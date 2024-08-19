//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
//
// Related Topics 链表 双指针 👍 1309 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L82_RemoveDuplicatesFromSortedListIi{
      
  public static void main(String[] args) {
       Solution solution = new L82_RemoveDuplicatesFromSortedListIi().new Solution();
       solution.deleteDuplicates(new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(2, new ListNode(3))))));
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
    /**
     * 遍历节点，寻找出现重复的位置，开始从重复位置遍历进行删除
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        ListNode cur = dummy;

        while (cur.next!=null&&cur.next.next!=null){
            int val = cur.next.val;
            if (cur.next.next.val==val){
                //有重复节点，通过while一直删除重复节点
                // -1->1(cur)->2(cur.next)->2(cur.next.next)->2->3
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            }else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}