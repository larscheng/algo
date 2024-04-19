//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1837 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L437_PathSumIii{
      
  public static void main(String[] args) {
       Solution solution = new L437_PathSumIii().new Solution();
       TreeNode node = new TreeNode(
               10,
               new TreeNode(5,new TreeNode(3,new TreeNode(5),new TreeNode(-2)),new TreeNode(2,null,new TreeNode(1))),
               new TreeNode(-3,null,new TreeNode(11))
               );

       TreeNode node2 = new TreeNode(
               5,
               new TreeNode(4,new TreeNode(11,new TreeNode(7),new TreeNode(2)),null),
               new TreeNode(8,new TreeNode(13),new TreeNode(4,new TreeNode(5),new TreeNode(1)))
       );

      /**
       *                    5
       *            4               8
       *        11     n     13         4
       *    7      2  n n   n  n     5      1
       */
      System.out.println(solution.pathSum(node, 8));
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
    public int pathSum(TreeNode root, int targetSum) {
        if (root==null){
            return 0;
        }
        //从当前节点出发
        int count = rootSum(root,targetSum);
        //从当前节点的左子树出发
        count += pathSum(root.left,targetSum);
        //从当前节点的右子树出发
        count += pathSum(root.right,targetSum);
        return count;
    }

    private int rootSum(TreeNode root, long targetSum) {
        int count = 0;

        if (root==null){
            return count;
        }
        if (root.val==targetSum){
            count++;
        }
        count += rootSum(root.left, targetSum - root.val);
        count += rootSum(root.right, targetSum - root.val);
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
    //迭代，这只是从根节点出发，实际需要从每个子树的根节点出发，复杂度为O(n^2)
        public int pathSum(TreeNode root, int targetSum) {
            if (root==null){
                return 0;
            }
            //从根节点出发
            int count = getCount(root, targetSum);

            return count;
        }

        private int getCount(TreeNode root, int targetSum) {
            int count =0;
            Stack<TreeNode>  stack = new Stack<>();
            Stack<Integer>  sum = new Stack<>();
            stack.push(root);
            sum.push(0);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                Integer pre_sum = sum.pop();

                Integer curSum = pre_sum + node.val;
                if (curSum == targetSum) {
                    count++;
                    curSum = 0;
                }
                if (curSum > targetSum) {
                    curSum = 0;
                }
                if (node.right != null) {
                    stack.push(node.right);
                    sum.push(curSum);
                }
                if (node.left != null) {
                    stack.push(node.left);
                    sum.push(curSum);
                }
            }
            return count;
        }
    }
}