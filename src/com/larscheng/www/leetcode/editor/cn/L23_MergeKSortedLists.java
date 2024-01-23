//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 2741 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class L23_MergeKSortedLists{
      
  public static void main(String[] args) {
      Solution solution = new L23_MergeKSortedLists().new Solution();
//      ListNode a = new ListNode(1,new ListNode(4, new ListNode(5,null)));
//      ListNode b = new ListNode(1,new ListNode(3, new ListNode(4,null)));
//      ListNode c = new ListNode(2,new ListNode(6, null));
//      System.out.println(solution.mergeKLists(new ListNode[]{a, b, c}));
  }

//leetcode submit region begin(Prohibit modification and deletion)

  //Definition for singly-linked list.
  public  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    /**
     * 将链表拆分为两两1组，一轮合并后链表数量减半
     * 如此往复，最终合并所有的链表
     */
    class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            return spiltAndMerge(lists, 0, lists.length - 1);
        }

        private ListNode spiltAndMerge(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            if (left > right) {
                return null;
            }
            int mid = left + (right - left) / 2;
            ListNode leftNode = spiltAndMerge(lists, left, mid);
            ListNode rightNode = spiltAndMerge(lists, mid + 1, right);
            return merge(leftNode, rightNode);
        }
        private ListNode merge(ListNode leftNode, ListNode rightNode){
            if (leftNode == null) {
                return rightNode;
            }
            if (rightNode == null) {
                return leftNode;
            }
            if (leftNode.val < rightNode.val) {
                leftNode.next = merge(leftNode.next, rightNode);
                return leftNode;
            } else {
                rightNode.next = merge(leftNode, rightNode.next);
                return rightNode;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 使用小顶堆维护记录每条链表，通过链表首个元素决定排序
     * 遍历堆，每次取出堆顶元素挂到新链表，然后将这条链表下一个元素继续放入堆中
     * O(kn*logn)/O(k)
     */
    class Solution1 {
        class  Status {
            int val;
            ListNode ptr;
            public Status(int val, ListNode ptr) {
                this.val = val;
                this.ptr = ptr;
            }
        }
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<Status> queue = new PriorityQueue<>((o1, o2) -> o1.val-o2.val);
            for (ListNode node : lists) {
                if (node!=null){
                    queue.offer(new Status(node.val,node));
                }
            }
            ListNode head = new ListNode(0);
            ListNode tail = head;
            while (!queue.isEmpty()){
                Status poll = queue.poll();
                tail.next = poll.ptr;
                tail = tail.next;
                if (poll.ptr.next!=null){
                    queue.offer(new Status(poll.ptr.next.val,poll.ptr.next));
                }
            }
            return head.next;
        }
    }
}