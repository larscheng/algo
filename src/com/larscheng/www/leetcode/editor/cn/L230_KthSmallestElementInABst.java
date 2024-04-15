//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,1,4,null,2], k = 1
//输出：1
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数为 n 。 
// 1 <= k <= n <= 10⁴ 
// 0 <= Node.val <= 10⁴ 
// 
//
// 
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 841 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.PriorityQueue;
import java.util.Stack;

public class L230_KthSmallestElementInABst{
      
  public static void main(String[] args) {
       Solution solution = new L230_KthSmallestElementInABst().new Solution();
       
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
    //中序遍历到第k个元素
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (--k==0){
                break;
            }
            root = root.right;
        }
        return root.val;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    //优先队列
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        helper(root,k,queue);

        return queue.peek();
    }

    private void helper(TreeNode root, int k, PriorityQueue<Integer> queue) {
        if (root==null){
            return;
        }
        helper(root.left,k,queue);

        queue.offer(root.val);
        if (queue.size() > k) {
            queue.poll();
        }

        helper(root.right,k,queue);
    }
}

}