//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
// 
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 10⁹ 
// 
//
// Related Topics 链表 双指针 👍 1012 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L61_RotateList{
      
  public static void main(String[] args) {
       Solution solution = new L61_RotateList().new Solution();
       ListNode a = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
      solution.rotateRight(a,2);
      ListNode b = new ListNode(0,new ListNode(1,new ListNode(2)));
       //solution.rotateRight(b,4);
      ListNode c = new ListNode(1,new ListNode(2));
//      solution.rotateRight(c,2);
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
     *
     * n个刻度的环形密码锁，旋转到指定位置
     * 将链表练成环，找到目标位置length-(k%length)，断开链表环
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k==0) {
            return head;
        }
        int len = 1;
        //统计链表节点个数
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            len++;
        }
        //成环
        current.next = head;
        //寻找断环节点
        int index = len - (k % len) ;
        for (int i = 1; i < index; i++) {
            head = head.next;
        }
        //断开链表环
        ListNode result = head.next; //记录倒数第k位（原始链表中倒数k+1位的next）
        head.next = null;   //断开环
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     *
     * 观察示例不难发现，右旋k位，实际上的链表操作为：记录原始链表第k位节点，最后一位节点next=头节点，倒数k+1位节点next=null、返回原链表倒数第k位节点
     * 所以核心思路即为：***寻找链表倒数第N个节点***-----
     *
     * 特殊情况1：k超出链表节点个数时，这里可以模拟绕圈跑步，跑半圈和跑一圈半，距离起点是一样长的
     * 假设链表节点个数为len，所以寻找链表倒数第N个节点，可以转换为 寻找链表倒数第***N%len*** 个节点
     * 特殊情况2：经过转换后k的值为0，则无需旋转
     *
     * 1.统计链表节点个数
     * 2.快慢指针寻找倒数第k+1个节点
     * 3.进行节点旋转操作
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result;
        int len = 1;
        //统计链表节点个数
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            len++;
        }
        k = k % len;
        if (k == 0) {
            return head;
        }
        //寻找倒数第k+1位
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            if (k <= 0) {
                slow = slow.next;
            }
            fast = fast.next;
            k--;
        }
        //进行旋转操作
        result = slow.next; //记录倒数第k位（原始链表中倒数k+1位的next）
        fast.next = head;   //最后一位节点next=头节点
        slow.next = null;   //倒数k+1位节点next=null
        return result;
    }
}

}