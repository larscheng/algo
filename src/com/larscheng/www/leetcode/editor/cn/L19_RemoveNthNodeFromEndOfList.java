//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 2838 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L19_RemoveNthNodeFromEndOfList{
      
  public static void main(String[] args) {
       Solution solution = new L19_RemoveNthNodeFromEndOfList().new Solution();
       solution.removeNthFromEnd(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null))))),2);
//       solution.removeNthFromEnd(new ListNode(1,new ListNode(2)),2);
  }
public static class ListNode {
 int val;
 ListNode next;
 ListNode() {}
 ListNode(int val) { this.val = val; }
 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    //双指针，快慢指针间隔n，两个指针一起向后移动，当快指针到达链表尾部时，慢指针即为待更新处
    //O(length)/O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy  = new ListNode(0,head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //先统计链表长度，删除倒数第n个节点，在已知总长度的情况下，遍历到第length-n个节点，修改指针

        //O(length)/O(1)
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int length = 0;
            ListNode cur = head;
            while (cur != null ) {
                cur = cur.next;
                length++;
            }
            ListNode dummy  = new ListNode(0,head);
            cur = dummy;
            for (int i = 1; i <= length-n ; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            return dummy.next;
        }
    }
}