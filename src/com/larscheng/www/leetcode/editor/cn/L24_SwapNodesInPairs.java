//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// Related Topics 递归 链表 👍 2093 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L24_SwapNodesInPairs{
      
  public static void main(String[] args) {
       Solution solution = new L24_SwapNodesInPairs().new Solution();
       Solution1 solution1 = new L24_SwapNodesInPairs().new Solution1();
       ListNode a = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
//       ListNode a = new ListNode(1);
       //solution.swapPairs(a);
       solution1.swapPairs(a);
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


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode others = head.next.next;
        node2.next = node1;
        node1.next = swapPairs(others);
        return node2;
    }
    /**
     * 递归
     * 假设others为已经交换完成的链表
     * [item1 -> item2 -> others] ===>  [item2 -> item1 -> others]
     * 交换结束后：item1的next为others，item2的next为item1
     *
     * 伪代码
     *  item1.next = 已交换完成的链表头节点
     *  item2.next = item1
     *  返回 item2
     *
     *
     * O(n)/O(1)
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //[1,2,3,4]
        ListNode temp = head.next;
        //其他节点递归交换 1->[4,3]
        head.next = swapPairs1(temp.next);
        //2->[1,4,3]
        temp.next = head;
        return temp;
    }

    public ListNode dfs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int temp = head.val;
        head.val = head.next.val;
        head.next.val = temp;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
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
    class Solution1 {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(0, head);
            ListNode pre = dummy;
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                ListNode t = cur.next;
                cur.next = t.next;
                t.next = cur;
                pre.next = t;
                pre = cur;
                cur = cur.next;
            }
            return dummy.next;
        }
    }

    class Solution12 {
        /**
         * 迭代，引入虚拟头节点指向head
         * 遍历链表,进行相邻两个节点交换,伪代码如下
         *
         * while(结束条件)
         *  item1.next = item2.next
         *  item2.next = item1
         *
         *
         * O(n)/O(1)
         */
        public ListNode swapPairs(ListNode head) {
            if (head==null||head.next==null){
                return head;
            }
            ListNode pre = new ListNode(0, head);
            ListNode temp = pre;

            while (temp.next != null && temp.next.next != null) {
                //[0,1,2,3,4]
                ListNode left = temp.next;
                ListNode right = temp.next.next;

                temp.next = right;
                //1->[3,4]
                left.next = right.next;
                //2->[1,3,4]
                right.next = left;
                //[1,3,4]
                temp = left;
                //pre:[0,2,1,3,4]
            }
            return pre.next;
        }
    }
}