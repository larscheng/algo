//给定一个二叉树 root ，返回其最大深度。 
//
// 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：root = [1,null,2]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量在 [0, 10⁴] 区间内。 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1745 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Deque;
import java.util.LinkedList;

public class L104_MaximumDepthOfBinaryTree{
      
  public static void main(String[] args) {
       Solution solution = new L104_MaximumDepthOfBinaryTree().new Solution();
       TreeNode node = new TreeNode(3,
               new TreeNode(9,null,null),
               new TreeNode(20,
                       new TreeNode(15,null,null),
                       new TreeNode(7,null,null)
               )
       );
       solution.maxDepth(node);
  }

public static class TreeNode {
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
         */
        public int maxDepth(TreeNode root) {
            if (root==null){
                return 0;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.push(root);
            Deque<TreeNode> temp;
            int depth = 0;
            while (!queue.isEmpty()){
                temp = new LinkedList<>();
                while (!queue.isEmpty()){
                    TreeNode node = queue.pop();
                    if (node.left!=null){
                        temp.push(node.left);
                    }
                    if (node.right!=null){
                        temp.push(node.right);
                    }
                }
                queue = temp;
                depth++;
            }
            return depth;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * 深度优先搜索，使用递归方式
     * 树的深度 = Max(左子树深度, 右子树深度) + 1
     * O(N)/O(N)
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth)+1;
    }
}

}