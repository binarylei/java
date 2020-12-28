//给定一个链表，判断链表中是否有环。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
// Related Topics 链表 双指针


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.<pre>
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }</pre>
 */

// 快慢指针。fast从head或head.next开始有什么不同？？？
public class Solution{
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}

//public class Solution{
//    public boolean hasCycle(ListNode head) {
//        if (head == null) return false;
//        ListNode fast = head.next;
//        ListNode slow = head;
//
//        while (fast != null && fast.next != null) {
//            if (fast == slow) return true;
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        return false;
//    }
//}

//public class Solution {
//    public boolean hasCycle(ListNode head) {
//        if (head == null) return false;
//
//        ListNode slow = head;
//        ListNode fast = head.next;
//        while (slow != fast) {
//            if (fast == null || fast.next == null) return false;
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return true;
//    }
//}

//方法一通过哈希set判断在遍历到null结点前是否有已经存在的结点
//public class Solution {
//    public boolean hasCycle(ListNode head) {
//        Set<ListNode> sets = new HashSet<>();
//        ListNode cur = head;
//        ListNode next;
//        while (cur != null) {
//            next = cur.next;
//            if (sets.contains(next)) return true;
//            sets.add(cur);
//            cur = next;
//        }
//        return false;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion

//class ListNode {
//    public int val;
//    public ListNode next;
//
//    public ListNode(int x) {
//        val = x;
//        next = null;
//    }
//}
