package com.binarylei.concurrent;

import org.junit.Test;

/**
 * This class implements a tree-like two-dimensionally linked skip
 * list in which the index levels are represented in separate
 * nodes from the base nodes holding data.  There are two reasons
 * for taking this approach instead of the usual array-based
 * structure: 1) Array based implementations seem to encounter
 * more complexity and overhead 2) We can use cheaper algorithms
 * for the heavily-traversed index lists than can be used for the
 * base lists.  Here's a picture of some of the basics for a
 * possible list with 2 levels of index:
 * <p>
 * Head nodes          Index nodes
 * +-+    right        +-+                      +-+
 * |2|---------------->| |--------------------->| |->null
 * +-+                 +-+                      +-+
 * | down              |                        |
 * v                   v                        v
 * +-+            +-+  +-+       +-+            +-+       +-+
 * |1|----------->| |->| |------>| |----------->| |------>| |->null
 * +-+            +-+  +-+       +-+            +-+       +-+
 * v              |    |         |              |         |
 * Nodes  next     v    v         v              v         v
 * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
 * | |->|A|->|B|->|C|->|D|->|E|->|F|->|G|->|H|->|I|->|J|->|K|->null
 * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
 * <p>
 * The base lists use a variant of the HM linked ordered set
 * algorithm. See Tim Harris, "A pragmatic implementation of
 * non-blocking linked lists"
 * http://www.cl.cam.ac.uk/~tlh20/publications.html and Maged
 * Michael "High Performance Dynamic Lock-Free Hash Tables and
 * List-Based Sets"
 * http://www.research.ibm.com/people/m/michael/pubs.htm.  The
 * basic idea in these lists is to mark the "next" pointers of
 * deleted nodes when deleting to avoid conflicts with concurrent
 * insertions, and when traversing to keep track of triples
 * (predecessor, node, successor) in order to detect when and how
 * to unlink these deleted nodes.
 * <p>
 * Rather than using mark-bits to mark list deletions (which can
 * be slow and space-intensive using AtomicMarkedReference), nodes
 * use direct CAS'able next pointers.  On deletion, instead of
 * marking a pointer, they splice in another node that can be
 * thought of as standing for a marked pointer (indicating this by
 * using otherwise impossible field values).  Using plain nodes
 * acts roughly like "boxed" implementations of marked pointers,
 * but uses new nodes only when nodes are deleted, not for every
 * link.  This requires less space and supports faster
 * traversal. Even if marked references were better supported by
 * JVMs, traversal using this technique might still be faster
 * because any search need only read ahead one more node than
 * otherwise required (to check for trailing marker) rather than
 * unmasking mark bits or whatever on each read.
 * <p>
 * This approach maintains the essential property needed in the HM
 * algorithm of changing the next-pointer of a deleted node so
 * that any other CAS of it will fail, but implements the idea by
 * changing the pointer to point to a different node, not by
 * marking it.  While it would be possible to further squeeze
 * space by defining marker nodes not to have key/value fields, it
 * isn't worth the extra type-testing overhead.  The deletion
 * markers are rarely encountered during traversal and are
 * normally quickly garbage collected. (Note that this technique
 * would not work well in systems without garbage collection.)
 * <p>
 * In addition to using deletion markers, the lists also use
 * nullness of value fields to indicate deletion, in a style
 * similar to typical lazy-deletion schemes.  If a node's value is
 * null, then it is considered logically deleted and ignored even
 * though it is still reachable. This maintains proper control of
 * concurrent replace vs delete operations -- an attempted replace
 * must fail if a delete beat it by nulling field, and a delete
 * must return the last non-null value held in the field. (Note:
 * Null, rather than some special marker, is used for value fields
 * here because it just so happens to mesh with the Map API
 * requirement that method get returns null if there is no
 * mapping, which allows nodes to remain concurrently readable
 * even when deleted. Using any other marker value here would be
 * messy at best.)
 * <p>
 * Here's the sequence of events for a deletion of node n with
 * predecessor b and successor f, initially:
 * <p>
 * +------+       +------+      +------+
 * ...  |   b  |------>|   n  |----->|   f  | ...
 * +------+       +------+      +------+
 * <p>
 * 1. CAS n's value field from non-null to null.
 * From this point on, no public operations encountering
 * the node consider this mapping to exist. However, other
 * ongoing insertions and deletions might still modify
 * n's next pointer.
 * <p>
 * 2. CAS n's next pointer to point to a new marker node.
 * From this point on, no other nodes can be appended to n.
 * which avoids deletion errors in CAS-based linked lists.
 * <p>
 * +------+       +------+      +------+       +------+
 * ...  |   b  |------>|   n  |----->|marker|------>|   f  | ...
 * +------+       +------+      +------+       +------+
 * <p>
 * 3. CAS b's next pointer over both n and its marker.
 * From this point on, no new traversals will encounter n,
 * and it can eventually be GCed.
 * +------+                                    +------+
 * ...  |   b  |----------------------------------->|   f  | ...
 * +------+                                    +------+
 * <p>
 * A failure at step 1 leads to simple retry due to a lost race
 * with another operation. Steps 2-3 can fail because some other
 * thread noticed during a traversal a node with null value and
 * helped out by marking and/or unlinking.  This helping-out
 * ensures that no thread can become stuck waiting for progress of
 * the deleting thread.  The use of marker nodes slightly
 * complicates helping-out code because traversals must track
 * consistent reads of up to four nodes (b, n, marker, f), not
 * just (b, n, f), although the next field of a marker is
 * immutable, and once a next field is CAS'ed to point to a
 * marker, it never again changes, so this requires less care.
 * <p>
 * Skip lists add indexing to this scheme, so that the base-level
 * traversals start close to the locations being found, inserted
 * or deleted -- usually base level traversals only traverse a few
 * nodes. This doesn't change the basic algorithm except for the
 * need to make sure base traversals start at predecessors (here,
 * b) that are not (structurally) deleted, otherwise retrying
 * after processing the deletion.
 * <p>
 * Index levels are maintained as lists with volatile next fields,
 * using CAS to link and unlink.  Races are allowed in index-list
 * operations that can (rarely) fail to link in a new index node
 * or delete one. (We can't do this of course for data nodes.)
 * However, even when this happens, the index lists remain sorted,
 * so correctly serve as indices.  This can impact performance,
 * but since skip lists are probabilistic anyway, the net result
 * is that under contention, the effective "p" value may be lower
 * than its nominal value. And race windows are kept small enough
 * that in practice these failures are rare, even under a lot of
 * contention.
 * <p>
 * The fact that retries (for both base and index lists) are
 * relatively cheap due to indexing allows some minor
 * simplifications of retry logic. Traversal restarts are
 * performed after most "helping-out" CASes. This isn't always
 * strictly necessary, but the implicit backoffs tend to help
 * reduce other downstream failed CAS's enough to outweigh restart
 * cost.  This worsens the worst case, but seems to improve even
 * highly contended cases.
 * <p>
 * Unlike most skip-list implementations, index insertion and
 * deletion here require a separate traversal pass occurring after
 * the base-level action, to add or remove index nodes.  This adds
 * to single-threaded overhead, but improves contended
 * multithreaded performance by narrowing interference windows,
 * and allows deletion to ensure that all index nodes will be made
 * unreachable upon return from a public remove operation, thus
 * avoiding unwanted garbage retention. This is more important
 * here than in some other data structures because we cannot null
 * out node fields referencing user keys since they might still be
 * read by other ongoing traversals.
 * <p>
 * Indexing uses skip list parameters that maintain good search
 * performance while using sparser-than-usual indices: The
 * hardwired parameters k=1, p=0.5 (see method doPut) mean
 * that about one-quarter of the nodes have indices. Of those that
 * do, half have one level, a quarter have two, and so on (see
 * Pugh's Skip List Cookbook, sec 3.4).  The expected total space
 * requirement for a map is slightly less than for the current
 * implementation of java.util.TreeMap.
 * <p>
 * Changing the level of the index (i.e, the height of the
 * tree-like structure) also uses CAS. The head index has initial
 * level/height of one. Creation of an index with height greater
 * than the current level adds a level to the head index by
 * CAS'ing on a new top-most head. To maintain good performance
 * after a lot of removals, deletion methods heuristically try to
 * reduce the height if the topmost levels appear to be empty.
 * This may encounter races in which it possible (but rare) to
 * reduce and "lose" a level just as it is about to contain an
 * index (that will then never be encountered). This does no
 * structural harm, and in practice appears to be a better option
 * than allowing unrestrained growth of levels.
 * <p>
 * The code for all this is more verbose than you'd like. Most
 * operations entail locating an element (or position to insert an
 * element). The code to do this can't be nicely factored out
 * because subsequent uses require a snapshot of predecessor
 * and/or successor and/or value fields which can't be returned
 * all at once, at least not without creating yet another object
 * to hold them -- creating such little objects is an especially
 * bad idea for basic internal search operations because it adds
 * to GC overhead.  (This is one of the few times I've wished Java
 * had macros.) Instead, some traversal code is interleaved within
 * insertion and removal operations.  The control logic to handle
 * all the retry conditions is sometimes twisty. Most search is
 * broken into 2 parts. findPredecessor() searches index nodes
 * only, returning a base-level predecessor of the key. findNode()
 * finishes out the base-level search. Even with this factoring,
 * there is a fair amount of near-duplication of code to handle
 * variants.
 * <p>
 * To produce random values without interference across threads,
 * we use within-JDK thread local random support (via the
 * "secondary seed", to avoid interference with user-level
 * ThreadLocalRandom.)
 * <p>
 * A previous version of this class wrapped non-comparable keys
 * with their comparators to emulate Comparables when using
 * comparators vs Comparables.  However, JVMs now appear to better
 * handle infusing comparator-vs-comparable choice into search
 * loops. Static method cpr(comparator, x, y) is used for all
 * comparisons, which works well as long as the comparator
 * argument is set up outside of loops (thus sometimes passed as
 * an argument to internal methods) to avoid field re-reads.
 * <p>
 * For explanation of algorithms sharing at least a couple of
 * features with this one, see Mikhail Fomitchev's thesis
 * (http://www.cs.yorku.ca/~mikhail/), Keir Fraser's thesis
 * (http://www.cl.cam.ac.uk/users/kaf24/), and Hakan Sundell's
 * thesis (http://www.cs.chalmers.se/~phs/).
 * <p>
 * Given the use of tree-like index nodes, you might wonder why
 * this doesn't use some kind of search tree instead, which would
 * support somewhat faster search operations. The reason is that
 * there are no known efficient lock-free insertion and deletion
 * algorithms for search trees. The immutability of the "down"
 * links of index nodes (as opposed to mutable "left" fields in
 * true trees) makes this tractable using only CAS operations.
 * <p>
 * Notation guide for local variables
 * Node:         b, n, f    for  predecessor, node, successor
 * Index:        q, r, d    for index node, right, down.
 * t          for another index node
 * Head:         h
 * Levels:       j
 * Keys:         k, key
 * Values:       v, value
 * Comparisons:  c
 */
public class A {

    /**
     * Ensure that a release propagates, even if there are other
     * in-progress acquires/releases.  This proceeds in the usual
     * way of trying to unparkSuccessor of head if it needs
     * signal. But if it does not, status is set to PROPAGATE to
     * ensure that upon release, propagation continues.
     * Additionally, we must loop in case a new node is added
     * while we are doing this. Also, unlike other uses of
     * unparkSuccessor, we need to know if CAS to reset status
     * fails, if so rechecking.
     */
    public void test() {

    }

    public static void main(String[] args) {
        float sum = 0.0f;
        for (int i = 0; i < 20000000; i++) {
            float x = 1.0f;
            sum += x;
        }
        System.out.println("sum is " + sum);
    }

    @Test
    public void kahanSummation() {
        float sum = 0.0f;
        float c = 0.0f;
        for (int i = 0; i < 20000000; i++) {
            float x = 1.0f;
            float y = x - c;
            float t = sum + y;
            c = (t - sum) - y;
            sum = t;
        }
        System.out.println("sum is " + sum);
    }
}
