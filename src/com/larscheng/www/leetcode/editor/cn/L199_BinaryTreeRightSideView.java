//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
// 
//
// 示例 2: 
//
// 
//输入: [1,null,3]
//输出: [1,3]
// 
//
// 示例 3: 
//
// 
//输入: []
//输出: []
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,100] 
// 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1056 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L199_BinaryTreeRightSideView{
      
  public static void main(String[] args) {
       Solution solution = new L199_BinaryTreeRightSideView().new Solution();
       TreeNode node = new TreeNode(1,
               new TreeNode(2,new TreeNode(6),new TreeNode(5)),
               new TreeNode(3,null,new TreeNode(4))
       );
      System.out.println(solution.rightSideView(node));
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
    //递归dfs，递归传递深度，每个深度都会对应一个待选元素，按照先序/中序遍历，收集每个深度对应的最右侧值
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root==null){
            return res;
        }
        helper(root,res,0);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res, int depth) {
        if (root==null){
            return;
        }
        if (res.size()<=depth){
            res.add(root.val);
        }else {
            res.set(depth,root.val);
        }

        helper(root.left, res, depth + 1);
        helper(root.right, res, depth + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //层序遍历，收集每层最右侧元素
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root==null){
                return res;
            }
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size ; i++) {
                    TreeNode node = queue.poll();
                    //队列先进先出，收集最后出来的
                    if (node.left!=null){
                        queue.add(node.left);
                    }
                    if (node.right!=null){
                        queue.add(node.right);
                    }
                    if (i==size-1){
                        res.add(node.val);
                    }
                }
            }
            return res;
        }
    }
}