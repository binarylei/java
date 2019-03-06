package com.github.binarylei.data.list;

/**
 * 单链表
 * <pre>
 *     prev                 current              next
 *  --------------      --------------     --------------
 * | value | next | -> | value | next | -> | value | next |
 *  --------------      --------------     --------------
 * </pre>
 *
 * @author: leigang
 * @version: 2018-12-07
 */
public class Node<E> {

    private E value;
    private Node next;

    public Node(E value) {
        this.value = value;
    }

    // 追加到最后一个元素
    public Node append(Node node) {
        Node tail = tail();
        tail.next(node);
        return this;
    }

    // 删除指定的节点
    public void remove(Node node) {
        Node prev = prev(node);
        if (prev != null) {
            prev.next = node.next;
        }
    }

    // 节点总数
    public int size() {
        int size = 1;
        Node current = this;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // 取出中间节点，偶数节点取中间两个节点的前一个节点，奇数节点取正中间的节点
    public Node mid() {
        Node fast = this;
        Node slow = this;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 链表反转
    public Node reverse() {
        Node prev = null;
        Node next = null;
        Node current = this;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // 有序链表合并，两个链表均升序排列，最终的结果也升序排列
    public static Node merge(Node<Integer> node1, Node<Integer> node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }

        Node<Integer> head = node1.value < node2.value ? node1 : node2;
        Node<Integer> cur1 = head == node1 ? node1 : node2; // 小
        Node<Integer> cur2 = head == node1 ? node2 : node1; // 大

        Node prev = null; // curl1 的前驱节点，小
        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                prev = cur1;
                cur1 = cur1.next;
            } else {
                // 将 curl2 插入到 prev 和 curl1 之间
                Node tmp = cur2.next;
                cur2.next = cur1;
                prev.next = cur2;
                prev = cur2;
                cur2 = tmp;
            }
        }
        prev.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    // 有序链表合并，两个链表均升序排列，最终的结果也升序排列
    public static Node mergeRecursive(Node<Integer> node1, Node<Integer> node2) {
        if (node1 == null || node2 == null) {
            return node1 != null ? node1 : node2;
        }
        Node head = null;
        if (node1.value > node2.value) {
            head = node2;
            head.next = mergeRecursive(node1, node2.next);
        } else {
            head = node1;
            head.next = mergeRecursive(node1.next, node2);
        }
        return head;
    }

    public Node sort() {
        return sort0(this);
    }

    // 归并排序(对链表排序尤其适合)
    private Node sort0(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = head.mid();
        Node left = head;
        Node right = mid.next;
        mid.next = null;

        Node merge = merge(sort0(left), sort0(right));
        return merge;
    }

    // 查找指定节点的上一个节点
    public Node prev(Node node) {
        Node prev = this;
        while (prev != null) {
            if (prev.next == node) {
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }

    // 查找最后一个节点，单链表的最后一个节点的 next=null
    public Node tail() {
        Node tail = this;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    // 设置当前节点的下一个节点
    public void next(Node next) {
        // 设置该节点的后继节点
        next.next = this.next;
        // 将该节点设置为当前节点的前驱节点
        this.next = next;
    }

    public Node next() {
        return next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
