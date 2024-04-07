//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
// 
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
//
// Related Topics 链表 双指针 分治 排序 归并排序 👍 2282 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L148_SortList{
      
  public static void main(String[] args) {
       Solution solution = new L148_SortList().new Solution();
       solution.sortList(new ListNode(-1,new ListNode(5,new ListNode(3,new ListNode(4,new ListNode(0))))));
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
    //归并排序-递归实现，空间复杂度O(logn)，不符合要求
    //归并排序-迭代实现，空间复杂度O(1)
    //O(nlogn)/O(1)
    public ListNode sortList(ListNode head) {
        int length = getLength(head);
        //从最小子集开始，两两合并
        int subLen = 1;
        ListNode dummy = new ListNode(0,head);
        while (subLen<length){
            //第一层循环：从最小子集subLen=1，2，4...开始两两合并
            ListNode pre = dummy;
            ListNode cur = dummy.next;

            while (cur!=null){
                //第一段头节点
                ListNode h1 = cur;
                //第二段头节点
                ListNode h2 = spiltList(h1, subLen);
                //下一轮起点
                cur = spiltList(h2,subLen);
                //下一轮pre
                pre = mergeList(h1, h2, pre);
            }
            subLen*=2;
        }
        return dummy.next;
    }

    private ListNode mergeList(ListNode h1, ListNode h2, ListNode pre) {
        //类似合并两个有序链表
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                pre.next = h1;
                h1 = h1.next;
            } else {
                pre.next = h2;
                h2 = h2.next;
            }
            pre = pre.next;
        }
        if (h1 == null) {
            pre.next = h2;
        }
        if (h2 == null) {
            pre.next = h1;
        }
        while (pre.next!=null){
            pre = pre.next;
        }
        return pre;
    }

    private ListNode spiltList(ListNode h1, int subLen) {
        //找下一段的头节点
        if (h1==null){
            return null;
        }
        ListNode temp = h1;
        for (int i = 1; i < subLen; i++) {
            if (temp==null){
                return null;
            }else {
                temp = temp.next;
            }
        }
        if (temp == null) {
           return null;
        }
        ListNode next = temp.next;
        temp.next = null;
        return next;
    }

    private int getLength(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while (cur!=null){
            cur = cur.next;
            length++;
        }
        return length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //节点收集、排序、构建升序链表
        //O(nlogn)/O(n)
        public ListNode sortList(ListNode head) {
            ListNode cur = head;
            List<Integer> list = new ArrayList<>();
            while (cur!=null){
                list.add(cur.val);
                cur = cur.next;
            }
            list = list.stream().sorted().collect(Collectors.toList());
            ListNode res = new ListNode(0);
            cur = res;
            for (Integer i : list) {
                cur.next = new ListNode(i);
                cur = cur.next;
            }
            return res.next;
        }
    }
}