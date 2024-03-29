//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
//
// Related Topics 递归 链表 👍 3475 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L21_MergeTwoSortedLists{
      
  public static void main(String[] args) {
       Solution solution = new L21_MergeTwoSortedLists().new Solution();
      solution.mergeTwoLists(new ListNode(1,new ListNode(3,new ListNode(5,null))),new ListNode(2,new ListNode(4,new ListNode(6,null))));
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
    //递归
    //O(m+n)/O(m+n)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1==null){
            return list2;
        }else if (list2==null){
            return list1;
        } else if (list1.val<list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //迭代，双指针依次比较两个节点的值，取小的放入新链表
        //O(m+n)/O(1)
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            ListNode res = new ListNode(-1, null);
            ListNode cur = res;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    cur.next = list1;
                    list1 = list1.next;
                } else {
                    cur.next = list2;
                    list2 = list2.next;
                }
                cur = cur.next;
            }
            if (list1==null){
                cur.next = list2;
            }
            if (list2==null){
                cur.next = list1;
            }
            return res.next;
        }
    }
}