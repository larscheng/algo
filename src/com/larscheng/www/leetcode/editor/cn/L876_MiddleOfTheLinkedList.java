//给你单链表的头结点 head ，请你找出并返回链表的中间结点。 
//
// 如果有两个中间结点，则返回第二个中间结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5]
//输出：[3,4,5]
//解释：链表只有一个中间结点，值为 3 。
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,2,3,4,5,6]
//输出：[4,5,6]
//解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
// 
//
// 
//
// 提示： 
//
// 
// 链表的结点数范围是 [1, 100] 
// 1 <= Node.val <= 100 
// 
//
// Related Topics 链表 双指针 👍 961 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L876_MiddleOfTheLinkedList{
      
  public static void main(String[] args) {
       Solution solution = new L876_MiddleOfTheLinkedList().new Solution();

      ListNode listNode = solution.middleNode(
              new ListNode(1, new ListNode(2, new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6)))))));
      System.out.println(listNode);
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
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}