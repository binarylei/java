package com.github.binarylei.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * We introduce here an approach that lies between the extremes of
 * never versus always updating queue (head and tail) pointers.
 * This offers a tradeoff between sometimes requiring extra
 * traversal steps to locate the first and/or last unmatched
 * nodes, versus the reduced overhead and contention of fewer
 * updates to queue pointers. For example, a possible snapshot of
 * a queue is:
 *
 *  head           tail
 *    |              |
 *    v              v
 *    M -> M -> U -> U -> U -> U
 *
 * The best value for this "slack" (the targeted maximum distance
 * between the value of "head" and the first unmatched node, and
 * similarly for "tail") is an empirical matter. We have found
 * that using very small constants in the range of 1-3 work best
 * over a range of platforms. Larger values introduce increasing
 * costs of cache misses and risks of long traversal chains, while
 * smaller values increase CAS contention and overhead.
 */
public class TransferQueueTest {

    /**
     * *** Overview of implementation ***
     * <p>
     * We use a threshold-based approach to updates, with a slack
     * threshold of two -- that is, we update head/tail when the
     * current pointer appears to be two or more steps away from the
     * first/last node. The slack value is hard-wired: a path greater
     * than one is naturally implemented by checking equality of
     * traversal pointers except when the list has only one element,
     * in which case we keep slack threshold at one. Avoiding tracking
     * explicit counts across method calls slightly simplifies an
     * already-messy implementation. Using randomization would
     * probably work better if there were a low-quality dirt-cheap
     * per-thread one available, but even ThreadLocalRandom is too
     * heavy for these purposes.
     * <p>
     * With such a small slack threshold value, it is not worthwhile
     * to augment this with path short-circuiting (i.e., unsplicing
     * interior nodes) except in the case of cancellation/removal (see
     * below).
     * <p>
     * We allow both the head and tail fields to be null before any
     * nodes are enqueued; initializing upon first append.  This
     * simplifies some other logic, as well as providing more
     * efficient explicit control paths instead of letting JVMs insert
     * implicit NullPointerExceptions when they are null.  While not
     * currently fully implemented, we also leave open the possibility
     * of re-nulling these fields when empty (which is complicated to
     * arrange, for little benefit.)
     * <p>
     * All enqueue/dequeue operations are handled by the single method
     * "xfer" with parameters indicating whether to act as some form
     * of offer, put, poll, take, or transfer (each possibly with
     * timeout). The relative complexity of using one monolithic
     * method outweighs the code bulk and maintenance problems of
     * using separate methods for each case.
     * <p>
     * Operation consists of up to three phases. The first is
     * implemented within method xfer, the second in tryAppend, and
     * the third in method awaitMatch.
     * <p>
     * 1. Try to match an existing node
     * <p>
     * Starting at head, skip already-matched nodes until finding
     * an unmatched node of opposite mode, if one exists, in which
     * case matching it and returning, also if necessary updating
     * head to one past the matched node (or the node itself if the
     * list has no other unmatched nodes). If the CAS misses, then
     * a loop retries advancing head by two steps until either
     * success or the slack is at most two. By requiring that each
     * attempt advances head by two (if applicable), we ensure that
     * the slack does not grow without bound. Traversals also check
     * if the initial head is now off-list, in which case they
     * start at the new head.
     * <p>
     * If no candidates are found and the call was untimed
     * poll/offer, (argument "how" is NOW) return.
     * <p>
     * 2. Try to append a new node (method tryAppend)
     * <p>
     * Starting at current tail pointer, find the actual last node
     * and try to append a new node (or if head was null, establish
     * the first node). Nodes can be appended only if their
     * predecessors are either already matched or are of the same
     * mode. If we detect otherwise, then a new node with opposite
     * mode must have been appended during traversal, so we must
     * restart at phase 1. The traversal and update steps are
     * otherwise similar to phase 1: Retrying upon CAS misses and
     * checking for staleness.  In particular, if a self-link is
     * encountered, then we can safely jump to a node on the list
     * by continuing the traversal at current head.
     * <p>
     * On successful append, if the call was ASYNC, return.
     * <p>
     * 3. Await match or cancellation (method awaitMatch)
     * <p>
     * Wait for another thread to match node; instead cancelling if
     * the current thread was interrupted or the wait timed out. On
     * multiprocessors, we use front-of-queue spinning: If a node
     * appears to be the first unmatched node in the queue, it
     * spins a bit before blocking. In either case, before blocking
     * it tries to unsplice any nodes between the current "head"
     * and the first unmatched node.
     * <p>
     * Front-of-queue spinning vastly improves performance of
     * heavily contended queues. And so long as it is relatively
     * brief and "quiet", spinning does not much impact performance
     * of less-contended queues.  During spins threads check their
     * interrupt status and generate a thread-local random number
     * to decide to occasionally perform a Thread.yield. While
     * yield has underdefined specs, we assume that it might help,
     * and will not hurt, in limiting impact of spinning on busy
     * systems.  We also use smaller (1/2) spins for nodes that are
     * not known to be front but whose predecessors have not
     * blocked -- these "chained" spins avoid artifacts of
     * front-of-queue rules which otherwise lead to alternating
     * nodes spinning vs blocking. Further, front threads that
     * represent phase changes (from data to request node or vice
     * versa) compared to their predecessors receive additional
     * chained spins, reflecting longer paths typically required to
     * unblock threads during phase changes.
     */
    public static void main(String[] args) {
        final BlockingQueue<Long> queue = new LinkedTransferQueue<>();

        Runnable offerTask = new Runnable() {
            public void run() {
                queue.offer(8L);
                System.out.println("offerTask thread has gone!");
            }

        };
        Runnable takeTask = new Runnable() {
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getId() + " " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable takeTaskInterrupted = new Runnable() {
            public void run() {
                Thread.currentThread().interrupt();
                try {
                    System.out.println(Thread.currentThread().getId() + " " + queue.take());
                } catch (InterruptedException e) {
                    System.out.println(e + " " + Thread.currentThread().getId());
                }
            }
        };

        new Thread(offerTask).start();
        new Thread(takeTask).start();
        new Thread(takeTaskInterrupted).start();
    }
}
