//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能翻转该如何解决？ 
//
// Related Topics 栈 链表 数学 👍 730 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L445_AddTwoNumbersIi{
      
  public static void main(String[] args) {
       Solution solution = new L445_AddTwoNumbersIi().new Solution();
       
  }
 public class ListNode {
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
     * 两种方法
     * - 链表反转后，进行加法计算，新节点通过头插法构成结果链表 O(n)/O(1)
     * - 压入栈结构，进行加法计算，头插法构建新链表 O(n)/O(n)
     * 为什么用头插法，因为原始数据高位在前，返回结果也要求高位在前，压入栈，是为了从低位开始计算，
     * 所以计算后的值，要么通过头插法保证低位在最后，要么通过反转或者栈处理
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1!=null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2!=null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        while (!stack1.isEmpty()||!stack2.isEmpty()||carry>0){
          int val = carry;
          if (!stack1.isEmpty()){val+=stack1.pop();}
          if (!stack2.isEmpty()){val+=stack2.pop();}
          carry = val/10;
          val = val%10;
          ListNode node = new ListNode(val);
          node.next = dummy.next;
          dummy.next = node;
        }
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}