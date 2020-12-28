//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.<pre>
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }</pre>
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        while (hasKNode(pre, k)) {
            pre = reverseKNode(pre, k);
        }
        return dummy.next;
    }

    // dummy之后是否有k个元素
    private boolean hasKNode(ListNode dummy, int k) {
        for (int i = 0; i < k; i++) {
            dummy = dummy.next;
            if (dummy == null) return false;
        }
        return true;
    }

    // dummy之后是否有k个元素翻转
    // dummy -> [node1, node2, node3] -> next
    private ListNode reverseKNode(ListNode dummy, int k) {
        ListNode head = dummy.next;

        ListNode pre = null;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = cur;
        dummy.next = pre;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }
}