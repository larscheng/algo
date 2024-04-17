//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
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
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
//
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1658 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class L114_FlattenBinaryTreeToLinkedList{
      
  public static void main(String[] args) {
       Solution solution = new L114_FlattenBinaryTreeToLinkedList().new Solution();
       TreeNode root = new TreeNode(
               1,
               new TreeNode(2, new TreeNode(3), new TreeNode(4)),
               new TreeNode(5, null, new TreeNode(6))
       );
       solution.flatten(root);
  }
 public  static  class TreeNode {
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
//要求单链表与先序遍历顺序相同
class Solution {
    //从根节点开始遍历，如果有左子树，则找左子树最右侧节点指向当前节点的右节点，当前节点左子树置为null，右子树指向原左子树
    //不借助额外空间，O(n)/O(1)
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    //先序遍历(递归/迭代)，在原树中组装链表
    //遍历和组装同时进行
    //O(n)/O(n)
    public void flatten(TreeNode root) {
        if (root==null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            //根->入栈：右-左=》根->出栈：左-右
            if (cur.right!=null){
                stack.push(cur.right);
            }

            if (cur.left!=null){
                stack.push(cur.left);
            }
            pre = cur;
        }
    }
}

    class Solution1 {
    //先序遍历(递归/迭代)，在原树中组装链表
    //O(n)/O(n)
    public void flatten(TreeNode root) {
        if (root==null){
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        //递归前序遍历（也可迭代）
        helper(root,list);
        //转链表
        for (int i = 0; i < list.size()-1; i++) {
            //i==0时开始改变root的子树结构
            TreeNode node = list.get(i);
            node.left = null;
            node.right = list.get(i + 1);;
        }
    }

    private void helper1(TreeNode root, List<TreeNode> list) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            list.add(pop);
            //根->入栈：右-左=》根->出栈：左-右
            if (pop.right!=null){
                stack.push(pop.right);
            }
            if (pop.left!=null){
                stack.push(pop.left);
            }
        }
    }
    private void helper(TreeNode root, List<TreeNode> list) {
        if (root==null){
            return;
        }
        list.add(root);
        helper(root.left, list);
        helper(root.right, list);
    }

}

}