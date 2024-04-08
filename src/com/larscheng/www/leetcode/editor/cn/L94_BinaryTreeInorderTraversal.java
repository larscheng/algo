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
import java.util.Objects;
import java.util.Stack;

public class L94_BinaryTreeInorderTraversal{
      
  public static void main(String[] args) {
       Solution solution = new L94_BinaryTreeInorderTraversal().new Solution();
      TreeNode node = new TreeNode(3,
                       new TreeNode(9,null,null),
                       new TreeNode(20,
                               new TreeNode(15,null,null),
                               new TreeNode(7,null,null)
                       )
               );
      System.out.println(solution.inorderTraversal(node));
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
    //颜色标记法，
    // 1、根节点先入栈，标记位白色
    // 2、弹出栈顶元素和其对应的颜色标记，跳过空值，
    //      如果为白色：将栈顶元素按照右子树（白色），当前节点（灰色），左子树（白色）的顺序入栈
    //      如果为灰色：记录节点值
    // 3、循环操作2
    // 这种栈结构中，最先出栈的必定是左子树，最先记录的节点必定是左叶子节点
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        if (root==null){
            return res;
        }
        stack.push("white");
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = (TreeNode) stack.pop();
            String color = (String) stack.pop();
            if (node == null){
                continue;
            }
            if (color.equals("white")){
                stack.push("white");
                stack.push(node.right);
                stack.push("gray");
                stack.push(node);
                stack.push("white");
                stack.push(node.left);
            }else {
                res.add(node.val);
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution3 {
        //O(n)/O(n)
        //迭代中序遍历，手动记录栈
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();

            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                //取出栈中的左子树
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }

    }
    class Solution2 {
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