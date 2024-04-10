//给你一棵二叉树的根节点，返回该树的 直径 。 
//
// 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。 
//
// 两节点之间路径的 长度 由它们之间边数表示。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,4,5]
//输出：3
//解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 10⁴] 内 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1516 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L543_DiameterOfBinaryTree{
      
  public static void main(String[] args) {
       Solution solution = new L543_DiameterOfBinaryTree().new Solution();
       
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
    //直径 = 最长路径的长度 = 最长路径经过的边数 = 最长路径节点数-1
    //根据根节点，取得当前根节点的左右子树深度，可得当前根节点所在最长路径的节点总数l+r+1
    //递归获取不同根节点所在最长路径的节点数最大值
    //根据节点数最大值-1，可得最长路径经过的边数，即为二叉树直径
    //O(n)/O(n)

    //树中节点大于1，最小直径为1
    int ans = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans - 1;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = depth(root.left);
        int r = depth(root.right);

        //当前根节点所在最长路径中的节点数
        ans = Math.max(ans, l + r + 1);

        //返回当前根节点(root)下子树的最大深度
        return Math.max(l, r) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}