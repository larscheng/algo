//给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。 
//
// 请你返回该链表所表示数字的 十进制值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [1,0,1]
//输出：5
//解释：二进制数 (101) 转化为十进制数 (5)
// 
//
// 示例 2： 
//
// 输入：head = [0]
//输出：0
// 
//
// 示例 3： 
//
// 输入：head = [1]
//输出：1
// 
//
// 示例 4： 
//
// 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
//输出：18880
// 
//
// 示例 5： 
//
// 输入：head = [0,0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 链表不为空。 
// 链表的结点总数不超过 30。 
// 每个结点的值不是 0 就是 1。 
// 
//
// Related Topics 链表 数学 👍 153 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1290_ConvertBinaryNumberInALinkedListToInteger{
      
  public static void main(String[] args) {
       Solution solution = new L1290_ConvertBinaryNumberInALinkedListToInteger().new Solution();
       
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
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = result * 2 + head.val;
            head = head.next;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int getDecimalValue(ListNode head) {
            StringBuilder stringBuilder = new StringBuilder();
            while (head!=null){
                stringBuilder.append(head.val);
                head = head.next;
            }
            String string = stringBuilder.reverse().toString();
            int result = 0;
            for (int i = 0; i < string.length(); i++) {
                int num = Integer.parseInt(String.valueOf(string.charAt(i)));
                if (num == 1) {
                    result += Math.pow(2, i);
                }
            }
            return result;
        }
    }

}