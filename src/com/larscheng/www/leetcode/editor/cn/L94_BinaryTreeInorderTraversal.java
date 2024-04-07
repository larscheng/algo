//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 2063 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L94_BinaryTreeInorderTraversal{
      
  public static void main(String[] args) {
       Solution solution = new L94_BinaryTreeInorderTraversal().new Solution();
      L94_BinaryTreeInorderTraversal.TreeNode node = new L94_BinaryTreeInorderTraversal.TreeNode(3,
                       new L94_BinaryTreeInorderTraversal.TreeNode(9,null,null),
                       new L94_BinaryTreeInorderTraversal.TreeNode(20,
                               new L94_BinaryTreeInorderTraversal.TreeNode(15,null,null),
                               new L94_BinaryTreeInorderTraversal.TreeNode(7,null,null)
                       )
               );
      L94_BinaryTreeInorderTraversal.TreeNode node1 = new L94_BinaryTreeInorderTraversal.TreeNode(1,
                       null,
                       new L94_BinaryTreeInorderTraversal.TreeNode(2,
                               new L94_BinaryTreeInorderTraversal.TreeNode(3,null,null),
                               null
                       )
               );
      solution.inorderTraversal(node1);
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
    //O(n)/O(n)
    //递归中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    //左节点+根节点+右节点
    //addall 空间复杂度高O(n^2)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }
}

}