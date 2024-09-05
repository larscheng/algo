//给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并
//返回这颗 二叉树 。 
//
// 
//
// 示例 1: 
// 
// 
//输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//输出：[3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入：inorder = [-1], postorder = [-1]
//输出：[-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= inorder.length <= 3000 
// postorder.length == inorder.length 
// -3000 <= inorder[i], postorder[i] <= 3000 
// inorder 和 postorder 都由 不同 的值组成 
// postorder 中每一个值都在 inorder 中 
// inorder 保证是树的中序遍历 
// postorder 保证是树的后序遍历 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1258 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class L106_ConstructBinaryTreeFromInorderAndPostorderTraversal{
      
  public static void main(String[] args) {
       Solution solution = new L106_ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
       solution.buildTree(new int[]{9,3,15,20,7},new int[]{9,15,7,20,3});
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
     *          3
     *     9        20
     *           15     7
     * @param inorder
     * @param postorder
     * @return
     */
    HashMap<Integer,Integer> map  = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return  build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    private TreeNode build(int[] inorder, int i_start, int i_end, int[] postorder, int p_start, int p_end) {
        if (i_start>i_end){
            return null;
        }
        int root_val = postorder[p_end];
        int root_index= map.get(root_val);
        int left_size = root_index - i_start;

        TreeNode root = new TreeNode(root_val);

        root.left = build(inorder, i_start, root_index - 1, postorder, p_start, p_start + left_size - 1);
        root.right = build(inorder, root_index + 1, i_end, postorder, p_start + left_size, p_end - 1);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     *          3
     *     9        20
     *           15     7
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length==0||postorder.length==0) {
            return null;
        }

        int rootVal = postorder[postorder.length-1];

        int index = Arrays.stream(inorder).boxed().collect(Collectors.toList()).indexOf(rootVal);

        TreeNode root = new TreeNode(rootVal);

        //[l,l,l,l,r,r,r,root]
        //[l,l,l,l,root,r,r,r]
        root.left = buildTree(Arrays.copyOf(inorder, index), Arrays.copyOf(postorder,index));

        root.right = buildTree(Arrays.copyOfRange(inorder, index+1, inorder.length), Arrays.copyOfRange(postorder, index, postorder.length-1));

        return root;
    }
}

}