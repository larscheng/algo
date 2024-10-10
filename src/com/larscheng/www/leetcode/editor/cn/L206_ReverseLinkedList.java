//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//
// 
// 
// 
// 
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
//
// Related Topics 递归 链表 👍 3541 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L206_ReverseLinkedList{
      
  public static void main(String[] args) {
       Solution solution = new L206_ReverseLinkedList().new Solution();
      ListNode listNode = solution.reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null)))));

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
    //递归,元素依次压栈，从最后一个元素开始回溯，修改指向关系，断开原始指针
    //O(n)/O(n)
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        //反转指向
        head.next.next = head;
        //断开原本的指向
        head.next = null;
        return newHead;
    }

    ListNode temp = null;

    /**
     * 递归反转前N个节点
     */
    public ListNode reverseN(ListNode head,int n) {
        if (n==1){
            temp = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next,n-1);
        //反转指向
        head.next.next = head;
        //断开原本的指向
        head.next = temp;
        return newHead;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

class Solution2 {
    //迭代,组装一个新链表
    //O(n)/O(n)
    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        for (ListNode i = head; i !=null ; i=i.next) {
            res = new ListNode(i.val, res);
        }
        return res;
    }
}
class Solution1 {
    //迭代，不断反转指向关系
    //O(n)/O(1)
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //保留原始结构
            ListNode next = cur.next;

            //反转指向
            cur.next = pre;

            //继续遍历
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseN(ListNode head,int n) {
        ListNode cur = head;
        ListNode pre = null;
        while (n>0){
            //保留原始结构
            ListNode next = cur.next;
            //反转指向
            cur.next = pre;
            //继续遍历
            pre = cur;
            cur = next;
            n--;
        }
        //head是反转后的最后一个节点，cur是第n+1个节点，两部分要连起来
        head.next = cur;
        return pre;
    }

}

}