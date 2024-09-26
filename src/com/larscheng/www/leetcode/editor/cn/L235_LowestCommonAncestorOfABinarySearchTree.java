//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1276 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L235_LowestCommonAncestorOfABinarySearchTree{
      
  public static void main(String[] args) {
       Solution solution = new L235_LowestCommonAncestorOfABinarySearchTree().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     * 前提：p、q都在二叉搜索树中
     *
     * 二叉搜索树特点：左子树<根<右子树
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int left = Math.min(p.val,q.val);
        int right = Math.max(p.val,q.val);
        return find(root,left,right);
    }

    private TreeNode find(TreeNode root, int left, int right) {
        if (root==null){
            return null;
        }
        //当前节点比最大节点大，去左子树寻找
        if (root.val>right){
            return find(root.left,left,right);
        }
        //当前节点比最小节点小，去右子树寻找
        if (root.val<left){
            return find(root.right,left,right);
        }
        //当前节点就在两节点中间，当前节点就是最近公共祖先
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}