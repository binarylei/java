package com.github.binarylei.data.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单链表测试
 *
 * @author: leigang
 * @version: 2018-12-07
 */
public class NodeTest {

    @Test
    public void test1() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.append(n2).append(n3);
        Assert.assertEquals(3, n1.next().next().getValue());
        Assert.assertEquals(3, n1.tail().getValue());
        Assert.assertEquals(2, n1.prev(n3).getValue());
        Assert.assertEquals(3, n1.size());

        n1.remove(n2);
        Assert.assertEquals(3, n1.next().getValue());
        Assert.assertEquals(1, n1.mid().getValue());

        n1.next(n2);
        Assert.assertEquals(3, n1.next().next().getValue());
        Assert.assertEquals(2, n1.mid().getValue());

        Node reverse = n1.reverse();
        Assert.assertEquals(3, reverse.getValue());
        Assert.assertEquals(2, reverse.next().getValue());
        Assert.assertEquals(1, reverse.next().next().getValue());
    }

    @Test
    public void mergeTest() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.append(n3).append(n5);
        n2.append(n4).append(n6);

        Node merge1 = Node.merge(n1, n2);
        Assert.assertEquals(6, merge1.size());
        Assert.assertEquals(2, merge1.next().getValue());
    }

    @Test
    public void mergeRecurseTest() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.append(n3).append(n5);
        n2.append(n4).append(n6);

        Node merge2 = Node.mergeRecursive(n1, n2);
        Assert.assertEquals(6, merge2.size());
        Assert.assertEquals(2, merge2.next().getValue());
    }

    @Test
    public void sortTest() {
        Node head = new Node(2)
                .append(new Node(3))
                .append(new Node(4))
                .append(new Node(0))
                .append(new Node(5))
                .append(new Node(1));

        Node sort = head.sort();
        Assert.assertEquals(0, sort.getValue());
        Assert.assertEquals(1, sort.next().getValue());
        Assert.assertEquals(2, sort.next().next().getValue());
        Assert.assertEquals(3, sort.next().next().next().getValue());
        Assert.assertEquals(4, sort.next().next().next().next().getValue());
        Assert.assertEquals(5, sort.next().next().next().next().next().getValue());
    }

    @Test
    public void test2() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        Assert.assertEquals(99, indexOf(arr, 99));
        Assert.assertEquals(-1, indexOf(arr, 100));
    }

    public int indexOf(int[] arr, int value) {
        int begin = 0;
        int end = arr.length - 1;
        int mid;

        while (true) {
            if (begin > end) {
                return -1;
            }
            mid = (begin + end) / 2;
            int midValue = arr[mid];
            if (value > midValue) {
                begin = mid + 1;
            } else if (value < midValue) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
    }

}
