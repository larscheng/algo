//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。 
//
// 假设二叉树中至少有一个节点。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [2,1,3]
//输出: 1
// 
//
// 示例 2: 
//
// 
//
// 
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1,10⁴] 
// 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 545 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Deque;
import java.util.LinkedList;

public class L513_FindBottomLeftTreeValue{
      
  public static void main(String[] args) {
       Solution solution = new L513_FindBottomLeftTreeValue().new Solution();
       TreeNode node = new TreeNode(3,
               new TreeNode(9,null,null),
               new TreeNode(20,
                       new TreeNode(15,null,null),
                       new TreeNode(7,null,null)
               )
       );
       solution.findBottomLeftValue(node);
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
    int curVal;
    int curHeight;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root,0);
        return curVal;
    }

    private void dfs(TreeNode root, int height) {
        if (root == null) {
            return;
        }
        height++;
        dfs(root.left, height);
        dfs(root.right, height);
        //当前递归中的高度 > 此时记录的高度 说明高度增加，最左节点需要记录为当前值
        if (height > curHeight) {
            curHeight = height;
            curVal = root.val;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * 找树左下角最左边的值，
     * 在广度优先搜索中，等同于找最后一层左起第1个节点，或者最后一层右起最后1个节点
     * > 广度优先搜索使用队列，有先进先出特性，所以每层右节点先入队列，然后遍历到最后1个就是左起第1个元素
     *
     * 在深度优先搜索中，等同于找最大高度中的第1个左节点
     * > 后序遍历+递归实现，全局变量记录当前已检查的高度，和当前高度的左节点值，
     * > 检查递归过程中的高度和当前记录的已检查高度，前者大，则收集新的左节点值
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null){
            return 0;
        }
        int result = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.right!=null){
                queue.offer(node.right);
            }
            if (node.left!=null){
                queue.offer(node.left);
            }
            result = node.val;
        }
        return result;
    }
}

}