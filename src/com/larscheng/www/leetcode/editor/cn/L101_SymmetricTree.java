//给你一个二叉树的根节点 root ， 检查它是否轴对称。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,2,null,3,null,3]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 1000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？ 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 2693 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L101_SymmetricTree{
      
  public static void main(String[] args) {
       Solution solution = new L101_SymmetricTree().new Solution();
       
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
// 对称的规律
    // left.left == right.right
class Solution {
    //递归检查左子树节点与右子树节点是否对称
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return dfs(root.left,root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            //不匹配
            return false;
        }
        if (left.val != right.val) {
            //不匹配
            return false;
        }
        //递归检查子树
        return dfs(left.left,right.right)&&dfs(left.right,right.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //迭代遍历，根据对称规律，将待比较的元素放进栈或者队列，依次比较
        public boolean isSymmetric(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return true;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root.left);
            stack.push(root.right);
            while (!stack.isEmpty()){
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                if (right==null&&left==null){
                    continue;
                }
                if (right==null||left==null){
                    return false;
                }
                if (right.val!=left.val){
                    return false;
                }

                stack.push(left.left);
                stack.push(right.right);

                stack.push(left.right);
                stack.push(right.left);
            }
            return true;
        }
    }
}