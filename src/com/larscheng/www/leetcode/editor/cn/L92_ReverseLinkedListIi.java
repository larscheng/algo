//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
//
// Related Topics 链表 👍 1809 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L92_ReverseLinkedListIi{
      
  public static void main(String[] args) {
       Solution solution = new L92_ReverseLinkedListIi().new Solution();
       ListNode a = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6))))));
       solution.reverseBetween(a,2,5);
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
     * 递归思路
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left==1) {
            return reverseN(head, right);
        }
        //递归快进到反转开始位置，此时相对位置发生变化
        // 1       2       3       4       5
        //head - node1 - node2 - node3 - node4
        head.next = reverseBetween(head.next, left-1, right-1);
        return head;
    }

    ListNode temp = null;//记录反转区间后面的首个节点，用于连接两部分链表
    private ListNode reverseN(ListNode head, int n) {
        if (n==1) {
            temp = head.next;
            return head;
        }
        //从head.next开始反转n-1个节点
        ListNode newhead = reverseN(head.next,n-1);
        //反转后head就是最后1个节点
        head.next.next = head;
        //反转后的最后1个节点，的next就是区间外的temp
        head.next  = temp;
        return newhead;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        /**
         * 迭代思路2
         * https://blog.csdn.net/Captain823Jack/article/details/141441361
         * 先考虑如何反转前n个节点，可以通过控制反转结束位置
         * 反转区间就转化为---》移动到区间开始位置，从开始位置反转right-left+1个节点
         *
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || head.next == null) {
                return head;
            }
            if (left == 1) {
                return reverseN(head, right);
            }
            ListNode dummy = new ListNode(0, head);
            ListNode temp = dummy.next;
            for (int i = 1; i < left - 1; i++) {
                temp = temp.next;
            }
            temp.next = reverseN(temp.next, right - left + 1);
            return dummy.next;
        }

        public ListNode reverseN(ListNode head , int n) {
            ListNode cur = head;
            ListNode pre = null;

            while (n>0){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                n--;
            }
            //head是反转后的最后1个节点，cur是区间外的第一个节点
            //[node1 -> node2 -> node3] -> node4
            //node4(cur) <- [node1 <- node2 <- node3]
            head.next = cur;
            return pre;
        }
    }
class Solution1 {
    /**
     * 迭代
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        //找到左范围节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            //pre：待反转区第一个节点的前一个节点
            //cur：待反转区第一个节点
            //next:cur的下一个节点
            //....[pre][cur][next]....
            //pre->next,next->cur,cur->next.next

            // pre -> cur -> next -> other
            //         ｜----------------
            //   --------------        ｜
            //  |      ｜       ↓       ↓
            // pre    cur    next   other(next.next)
            //         ↑       ｜
            //         ---------
            // pre -> cur -> next -> other
            // other <- cur <- next <- pre
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}

}