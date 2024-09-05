package com.larscheng.www.leetcode.editor.cn;

/**
 * @author zhengqilong
 * @version 1.0
 * @date 2024/9/4 7:51 PM
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
