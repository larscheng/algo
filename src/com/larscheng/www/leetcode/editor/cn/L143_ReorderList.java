//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// 
//L0 → L1 → … → Ln - 1 → Ln
// 
//
// 请将其重新排列后变为： 
//
// 
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
//
// Related Topics 栈 递归 链表 双指针 👍 1490 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L143_ReorderList{
      
  public static void main(String[] args) {
      Solution solution = new L143_ReorderList().new Solution();
      ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,null))));
      solution.reorderList(node);
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
     * 1，2，3，4，5
     * 1，5，2，4，3
     *
     * 快慢指针找到中间节点，然后将中间节点及其后节点放入栈
     * 将栈中节点依次取出，插入到原链表对应每个节点之后
     */
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //循环结束，slow为中间节点
        while (fast!=null&&fast.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }

        Stack<ListNode> stack = new Stack<>();
        while (slow!=null) {
            stack.push(slow);
            slow = slow.next;
        }

        //ListNode dummy = new ListNode(0,head);
        //ListNode point = dummy.next;
        swap(stack, head);


    }

    private void swap(Stack<ListNode> stack, ListNode point) {
        ListNode popNode;
        while (!stack.isEmpty()) {
            popNode = stack.pop();
            //point等于popNode:已经到最后一个节点（奇数个）
            //point.next等于popNode:左侧最后一个节点（偶数个）
            if (point == popNode || point.next == popNode) {
                popNode.next=null;
                break;
            }

            popNode.next = point.next;
            point.next = popNode;

            point = point.next.next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}