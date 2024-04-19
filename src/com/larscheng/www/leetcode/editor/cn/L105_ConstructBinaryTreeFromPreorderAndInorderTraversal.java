//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
// 
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 2278 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L105_ConstructBinaryTreeFromPreorderAndInorderTraversal{
      
  public static void main(String[] args) {
       Solution solution = new L105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
       solution.buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
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
    //前序遍历确定根节点，中序遍历确定左右子树，递归构建二叉树
    //O(n)/O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length==0||inorder.length==0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int rootIndex = Arrays.stream(inorder).boxed().collect(Collectors.toList()).indexOf(root.val);

        //[1,rootIndex+1]都是左子树
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, rootIndex+1), Arrays.copyOf(inorder, rootIndex));
        //[rootIndex+1,lenght]都是右子树
        root.right = buildTree(Arrays.copyOfRange(preorder, rootIndex+1, preorder.length), Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));

        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}