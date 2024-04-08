//给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [2,1,3]
//输出：[2,3,1]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1782 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.LinkedList;

public class L226_InvertBinaryTree{
      
  public static void main(String[] args) {
       Solution solution = new L226_InvertBinaryTree().new Solution();
      TreeNode node = new TreeNode(3,
              new TreeNode(9, null, null),
              new TreeNode(20,
                      new TreeNode(15, null, null),
                      new TreeNode(7, null, null)
              )
      );
      solution.invertTree(node);

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
    //迭代，广度优先遍历，队列记录每层的非叶子节点，遍历队列，进行交换左右子树
    //O(n)/O(n)
    public TreeNode invertTree(TreeNode root) {
        if (root==null){
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;

            if (temp.left!=null){
                queue.add(temp.left);
            }
            if (temp.right!=null){
                queue.add(temp.right);
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    //递归，分解问题，利用递归栈分别处理左右子树，最终处理根节点左右子树
    //O(n)/O(n)
    public TreeNode invertTree(TreeNode root) {
        if (root==null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }


}
    class Solution1 {
        //递归1次遍历，交换每个节点的左/右子节点位置，跳过叶子节点
        //O(n)/O(n)
        public TreeNode invertTree(TreeNode root) {
            invert(root);
            return root;
        }

        private void invert(TreeNode root) {
            if (root==null||(root.left==null&&root.right==null)){
                return;
            }
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            invert(root.left);
            invert(root.right);
        }
    }
}