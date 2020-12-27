package com.binarylei.algorithm.struct.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 1. 链表反转
 * 2. 获取中间结点
 * 3. 有序链表合并
 *
 * @author binarylei
 * @version 2020-02-23
 */
public class LinkedListAlgo {

    private Node head;
    private Node tail;

    public LinkedListAlgo add(Object value) {
        Node node = new Node(value, null);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        return this;
    }

    public static Node asList(Object... values) {
        LinkedListAlgo list = new LinkedListAlgo();
        Arrays.stream(values).forEach(list::add);
        return list.head;
    }

    // 链表反转
    public static Node reverse(Node node) {
        if (node == null) return null;

        Node curr = node, pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    // 获取中间结点
    public static Node midNode(Node node) {
        if (node == null) return null;

        Node fast = node.next, slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 两个有序链表合并
     * node1, node2 都是升序排序，返回的结果也是升序排序
     */
    public static Node mergeSortedLists(Node node1, Node node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;

        // TODO 优化：可以将头结点的判断提到while循环外，避免每次循环都判断一次
        Node head = null, curr = null, curr1 = node1, curr2 = node2;
        while (curr1 != null && curr2 != null) {
            Node minNode;
            // node1 > node2
            if (compare(curr1, curr2) > 0) {
                minNode = curr2;
                curr2 = curr2.next;
            } else {
                minNode = curr1;
                curr1 = curr1.next;
            }
            if (head == null) {
                head = curr = minNode;
            } else {
                curr.next = minNode;
                curr = minNode;
            }
        }
        if (curr1 != null) {
            curr.next = curr1;
        } else if (curr2 != null) {
            curr.next = curr2;
        }

        return head;
    }

    // 利用头结点，优化代码
    public static Node mergeSortedLists2(Node node1, Node node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;

        // TODO 优化：可以将头结点的判断提到while循环外，避免每次循环都判断一次
        Node head = new Node(), curr = head, curr1 = node1, curr2 = node2;
        while (curr1 != null && curr2 != null) {
            Node minNode;
            // node1 > node2
            if (compare(curr1, curr2) > 0) {
                minNode = curr2;
                curr2 = curr2.next;
            } else {
                minNode = curr1;
                curr1 = curr1.next;
            }
            // 如果是哨兵结点，就不需要对头结点单独判断
            curr.next = minNode;
            curr = minNode;
        }
        if (curr1 != null) {
            curr.next = curr1;
        } else if (curr2 != null) {
            curr.next = curr2;
        }

        return head.next;
    }

    /**
     * node1 > node2 正数
     * node1 = node2 相等
     * node1 < node2 负数
     */
    public static int compare(Node node1, Node node2) {
        if (!(node1.item instanceof Comparable) || !(node2.item instanceof Comparable)) {
            return 0;
        }

        Comparable item1 = (Comparable) node1.item;
        Comparable item2 = (Comparable) node2.item;
        return Objects.compare(item1, item2, (o1, o2) -> o1.compareTo(o2));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node {
        private Object item;
        private Node next;

        public Node(Object item) {
            this.item = item;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = this;
            while (curr != null) {
                Node next = curr.next;
                sb.append(String.format(
                        "%s%s", curr.item, next != null ? ", " : ""));
                curr = next;
            }
            return sb.toString();
        }

    }

    @Test
    public void testReverse() {
        Node head = asList(1, 2, 3, 4, 5, 6);
        Assert.assertEquals("1, 2, 3, 4, 5, 6", head.toString());

        Node reverseNode = reverse(head);
        Assert.assertEquals("6, 5, 4, 3, 2, 1", reverseNode.toString());
    }

    @Test
    public void testMidNode() {
        Assert.assertEquals(1, midNode(asList(1)).item);
        Assert.assertEquals(1, midNode(asList(1, 2)).item);
        Assert.assertEquals(2, midNode(asList(1, 2, 3)).item);
        Assert.assertEquals(2, midNode(asList(1, 2, 3, 4)).item);
        Assert.assertEquals(3, midNode(asList(1, 2, 3, 4, 5)).item);
    }

    @Test
    public void testCompare() {
        Assert.assertTrue(compare(new Node(1), new Node(2)) < 0);
        Assert.assertTrue(compare(new Node(1), new Node(1)) == 0);
        Assert.assertTrue(compare(new Node(2), new Node(1)) > 0);
    }

    @Test
    public void testMergeSortedLists() {
        Assert.assertEquals("1, 2", mergeSortedLists(
                asList(1, 2), null).toString());
        Assert.assertEquals("1, 2", mergeSortedLists(
                null, asList(1, 2)).toString());

        Assert.assertEquals("1, 1, 2, 7, 8", mergeSortedLists(
                asList(1, 7, 8), asList(1, 2)).toString());
        Assert.assertEquals("1, 1, 2, 7, 8", mergeSortedLists(
                asList(1, 2), asList(1, 7, 8)).toString());
    }

    @Test
    public void testMergeSortedLists2() {
        Assert.assertEquals("1, 1, 2, 7, 8", mergeSortedLists2(
                asList(1, 7, 8), asList(1, 2)).toString());
        Assert.assertEquals("1, 1, 2, 7, 8", mergeSortedLists2(
                asList(1, 2), asList(1, 7, 8)).toString());
    }
}
