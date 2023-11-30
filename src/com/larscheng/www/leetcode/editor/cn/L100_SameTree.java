//给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 
//
// 示例 1： 
// 
// 
//输入：p = [1,2,3], q = [1,2,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：p = [1,2], q = [1,null,2]
//输出：false
// 
//
// 示例 3： 
// 
// 
//输入：p = [1,2,1], q = [1,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 两棵树上的节点数目都在范围 [0, 100] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1100 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Deque;
import java.util.LinkedList;

public class L100_SameTree{
      
  public static void main(String[] args) {
       Solution solution = new L100_SameTree().new Solution();
       
  }
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * 队列-广度优先搜索
     *
     * O(N)/O(N)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode poll1 = queue1.poll();
            TreeNode poll2 = queue2.poll();
            if (poll1.val!=poll2.val){
                return false;
            }
            TreeNode left1 = poll1.left;
            TreeNode right1 = poll1.right;
            TreeNode left2 = poll2.left;
            TreeNode right2 = poll2.right;
            if ((left1 == null && left2 != null) || (left2 == null && left1 != null)) {
                return false;
            }
            if ((right1 == null && right2 != null) || (right2 == null && right1 != null)) {
                return false;
            }
            if (left1!=null){
                queue1.push(left1);
            }
            if (right1!=null){
                queue1.push(right1);
            }
            if (left2!=null){
                queue2.push(left2);
            }
            if (right2!=null){
                queue2.push(right2);
            }
        }
        return queue1.isEmpty()&&queue2.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        /**
         * 递归-深度优先搜索
         * 同时遍历两颗树，递归过程中判断2颗树的当前节点是否为空，如果不为空判断值是否相等
         * O(N)/O(1)
         */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}