//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
// 1. 使用哨兵节点：去除头节点的判断
// 2. slow.next = fast.next 不用判断最后一个节点
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        ListNode slow;
        ListNode fast;

        while (pre.next != null && pre.next.next != null) {
            slow = pre.next;
            fast = pre.next.next;

            pre.next = fast;
            slow.next = fast.next;
            fast.next = slow;
            pre = pre.next.next;
        }

        return dummyNode.next;
    }
}

//class Solution {
//    public ListNode swapPairs(ListNode head) {
//        if (head == null) return null;
//        ListNode slow = null;
//        ListNode fast = null;
//        ListNode cur = head;
//
//        ListNode retHead;
//        ListNode retTail;
//        if (cur.next == null) {
//            return head;
//        } else {
//            fast = head.next;
//            slow = cur;
//            cur = cur.next.next;
//
//            retHead = fast;
//            fast.next = slow;
//            retTail = slow;
//        }
//        while (cur != null && cur.next != null) {
//            fast = cur.next;
//            slow = cur;
//            cur = cur.next.next;
//
//            retTail.next = fast;
//            fast.next = slow;
//            retTail = slow;
//        }
//        if (cur != null) {
//            retTail.next = cur;
//            retTail = cur;
//        }
//        retTail.next = null;
//        return retHead;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}