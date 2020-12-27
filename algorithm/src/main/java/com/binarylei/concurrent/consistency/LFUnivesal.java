package com.binarylei.concurrent.consistency;

import lombok.NoArgsConstructor;

/**
 * @author binarylei
 * @version 2020-03-16
 */
public class LFUnivesal {

    private Node[] head;
    private Node tail;

    public LFUnivesal() {
        this.tail = new Node();
        tail.seq = 1;
        for (int i = 0; i < head.length; i++) {
            head[i] = tail;
        }
    }

    public Response apply(Invoc invoc) {
        int i = (int) Thread.currentThread().getId() % head.length;
        Node prefer = new Node(invoc);
        while (prefer.seq == 0) {
            Node before = Node.max(head);
            Node after = before.decideNext.decide(prefer);
            before.next = after;
            after.seq = before.seq + 1;
            head[i] = after;
        }

        SeqObject myObject = new SeqObject();
        Node current = tail.next;
        while (current != prefer) {
            myObject.apply(current.invoc);
            current = current.next;
        }

        return myObject.apply(invoc);
    }

    /**
     * @author binarylei
     * @version 2020-03-16
     */
    @NoArgsConstructor
    static
    class Node {

        private Invoc invoc;
        private Consensus<Node> decideNext;
        private Node next;
        private int seq;

        public Node(Invoc invoc) {
            this.invoc = invoc;
            decideNext = new Consensus<>();
            seq = 0;
        }

        public static Node max(Node[] array) {
            Node max = array[0];
            for (int i = 0; i < array.length; i++) {
                if (max.seq < array[i].seq)
                    max = array[i];
            }
            return max;
        }
    }

    private class Response {
    }

    private class Invoc {
    }

    private static class Consensus<T> {
        public T decide(T prefer) {
            return null;
        }
    }

    private class SeqObject {
        public Response apply(Invoc invoc) {
            return null;
        }
    }
}
