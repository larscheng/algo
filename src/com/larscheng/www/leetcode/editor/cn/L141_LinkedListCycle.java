//给你一个链表的头节点 head ，判断链表中是否有环。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。 
//
// 如果链表中存在环 ，则返回 true 。 否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 10⁴] 
// -10⁵ <= Node.val <= 10⁵ 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
//
// 
//
// 进阶：你能用 O(1)（即，常量）内存解决此问题吗？ 
//
// Related Topics 哈希表 链表 双指针 👍 2126 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

public class L141_LinkedListCycle{
      
  public static void main(String[] args) {
       Solution solution = new L141_LinkedListCycle().new Solution();
       
  }
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    //双指针，快慢指针 O(n)/O(1)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            //有环
            if (slow==fast){
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    public class Solution1 {
    //哈希表记录重复元素，O(n)/O(n)
        public boolean hasCycle(ListNode head) {
            Map<Integer,Integer> map = new HashMap<>();
            while (head!=null){
                if (map.containsKey(head.val)){
                    //存在环
                    return true;
                }
                map.put(head.val,head.val);
                head = head.next;
            }
            return false;
        }
    }
}