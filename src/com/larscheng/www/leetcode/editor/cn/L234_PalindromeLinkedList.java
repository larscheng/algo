//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
//
// Related Topics 栈 递归 链表 双指针 👍 1877 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L234_PalindromeLinkedList{
      
  public static void main(String[] args) {
       Solution solution = new L234_PalindromeLinkedList().new Solution();
     // System.out.println(solution.isPalindrome(new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, null))))));
      System.out.println(solution.isPalindrome(new ListNode(1, new ListNode(2, new ListNode(3,new ListNode(2, new ListNode(1, null)))))));
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
    //找到链表中间位，反转后半段
    //两段链表逐个元素比较，如果有不一样的则不符合要求
    //最终复原链表
    //O(n)/O(1)
    public boolean isPalindrome(ListNode head) {
        if (head.next==null){
            return true;
        }
        //找到中间节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //后半段反转 1,2,3,2,1
        ListNode pre = reverse(slow.next);

        ListNode node1 = head;
        ListNode node2 = pre;
        //两段链表比较
        while (node2 != null) {
            if (node1.val != node2.val) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        //恢复链表
        slow.next = reverse(pre);

        return true;
    }

    private ListNode reverse(ListNode head) {

        ListNode cur = head;
        ListNode pre = null;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //链表元素存储到额外空间，通过双指针遍历比较左右两侧的值
        //O(n)/O(n)
        public boolean isPalindrome(ListNode head) {
            if (head.next==null){
                return true;
            }
            List<Integer> temp = new ArrayList<>();
            while (head!=null){
                temp.add(head.val);
                head = head.next;
            }
            int left = 0,right= temp.size()-1;
            while (left <= right) {
                if (!temp.get(left++).equals(temp.get(right--))) {
                    return false;
                }
            }
            return true;
        }
    }
}