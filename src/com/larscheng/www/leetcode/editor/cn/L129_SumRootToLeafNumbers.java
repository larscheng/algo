//给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
//
// 
// 
// 每条从根节点到叶节点的路径都代表一个数字： 
// 
// 
//
// 
// 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。 
// 
//
// 计算从根节点到叶节点生成的 所有数字之和 。 
//
// 叶节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3]
//输出：25
//解释：
//从根到叶子节点路径 1->2 代表数字 12
//从根到叶子节点路径 1->3 代表数字 13
//因此，数字总和 = 12 + 13 = 25 
//
// 示例 2： 
// 
// 
//输入：root = [4,9,0,5,1]
//输出：1026
//解释：
//从根到叶子节点路径 4->9->5 代表数字 495
//从根到叶子节点路径 4->9->1 代表数字 491
//从根到叶子节点路径 4->0 代表数字 40
//因此，数字总和 = 495 + 491 + 40 = 1026
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 1000] 内 
// 0 <= Node.val <= 9 
// 树的深度不超过 10 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 698 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Deque;
import java.util.LinkedList;

public class L129_SumRootToLeafNumbers{
      
  public static void main(String[] args) {
       Solution solution = new L129_SumRootToLeafNumbers().new Solution();
       TreeNode node = new TreeNode(3,
               new TreeNode(9,null,null),
               new TreeNode(20,
                       new TreeNode(15,null,null),
                       new TreeNode(7,null,null)
               )
       );
       solution.sumNumbers(node);
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
    public int sumNumbers(TreeNode root) {
        if (root==null){
            return 0;
        }
        int result = 0;
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        Deque<Integer> sumQueue = new LinkedList<>();
        nodeQueue.offer(root);
        sumQueue.offer(root.val);
        while (!nodeQueue.isEmpty() && !sumQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer sum = sumQueue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left == null && right == null) {
                result += sum;
            }
            if (left != null) {
                nodeQueue.offer(left);
                sumQueue.offer(sum * 10 + left.val);
            }
            if (right != null) {
                nodeQueue.offer(right);
                sumQueue.offer(sum * 10 + right.val);
            }

        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int result) {
        if (root == null) {
            //左子树为空或者右子树为空
            return 0;
        }
        result = result * 10 + root.val;

        if (root.left == null && root.right == null) {
            //当前节点为叶子节点
            return result;
        } else {
            //非叶子节点
            return dfs(root.left, result) + dfs(root.right, result);
        }
    }
}

}