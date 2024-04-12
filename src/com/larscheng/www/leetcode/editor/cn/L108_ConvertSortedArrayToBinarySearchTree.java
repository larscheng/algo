//给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。 
//
// 
//
// 示例 1： 
// 
// 
//输入：nums = [-10,-3,0,5,9]
//输出：[0,-3,9,-10,null,5]
//解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
//
// 
//
// 示例 2： 
// 
// 
//输入：nums = [1,3]
//输出：[3,1]
//解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 按 严格递增 顺序排列 
// 
//
// Related Topics 树 二叉搜索树 数组 分治 二叉树 👍 1497 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L108_ConvertSortedArrayToBinarySearchTree{
      
  public static void main(String[] args) {
       Solution solution = new L108_ConvertSortedArrayToBinarySearchTree().new Solution();
       
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
    //O(n)/O(logn)
    //中序遍历：升序数组，+二叉平衡数=》可以确定根节点，根节点左右的数组元素均为左右子树
    public TreeNode sortedArrayToBST(int[] nums) {
        return convert(nums,0,nums.length-1);
    }

    private TreeNode convert(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //中间位置左边的数作为根节点
        int mid = left+(right-left)/2;
        // 中间位置右边的数作为根节点
        // int mid = left+(right-left+1)/2

        TreeNode root = new TreeNode(nums[mid]);
        root.left = convert(nums,left,mid-1);
        root.right = convert(nums,mid+1,right);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}