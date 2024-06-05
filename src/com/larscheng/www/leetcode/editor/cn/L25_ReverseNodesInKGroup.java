//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
//
// Related Topics 递归 链表 👍 2301 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L25_ReverseNodesInKGroup{
      
  public static void main(String[] args) {
       Solution solution = new L25_ReverseNodesInKGroup().new Solution();
      ListNode a = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
//      ListNode a = new ListNode(1,new ListNode(2,null));
//      solution.reverseKGroup(a,2);
      solution.reverseKGroup(a,3);
//      solution.reverseKGroup(new ListNode(1,new ListNode(2)),2);
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
     * 按k个1组，进行反转，递归找每个组，然后重复反转逻辑
     * 不足k个，不用反转
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        //找每1组的左右边界
        ListNode a=head,b=head;

        for (int i = 0; i < k; i++) {
            if (b==null) {
                //不足k个元素，不用反转直接返回
                return head;
            }
            b = b.next;
        }
        //组内反转，到b停止反转
        ListNode newHead = reverse(a,b);
        //反转后a为最后一个节点，其他的组接在a.next
        a.next = reverseKGroup(b, k);
        //返回头节点
        return newHead;
    }

    //迭代反转
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode currNode = a;
        ListNode pre = null;
        while (currNode!=b) {
            ListNode next =  currNode.next;
            //反转
            currNode.next = pre;
            //更新pre
            pre = currNode;
            //继续遍历
            currNode = next;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //迭代，统计总节点数，将节点分k个一组进行迭代，对单个组进行反转
        //反转时记录尾节点，用于连接下一组反转后的链表
        //最后将没有参与分组反转的节点连在尾部
        //O(n)/O(1)
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode cur = head;
            int length = 0;
            while (cur!=null){
                cur = cur.next;
                length++;
            }
            ListNode dummy = new ListNode(0);
            ListNode temp  = dummy;
            cur = head;
            for (int i = 0; i+k <= length ; i+=k) {
                ListNode pre = null;
                ListNode tail = null;
                for (int j = 0; j < k; j++) {
                    if (j==0){
                        tail = cur;
                    }
                    //保留原始结构
                    ListNode next = cur.next;
                    //反转指向
                    cur.next = pre;
                    //继续遍历
                    pre = cur;
                    cur = next;

                }
                temp.next = pre;
                temp = tail ;
            }
            if (cur!=null){
                temp.next = cur;
            }
            return dummy.next;
        }
    }
}