//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 2437 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L96_UniqueBinarySearchTrees{
      
  public static void main(String[] args) {
       Solution solution = new L96_UniqueBinarySearchTrees().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * G(n)表示 长度为n的序列可构成不同的二叉搜索数的个数
     * f(i,n)表示 以i为根，长度为n的序列可构成的二叉搜索数的个数
     *
     * G(n)的值可以通过遍历所有i，以i为根节点构造二叉搜索数，累加所有二叉搜索数的总数
     * G(n) = f(1,n)+f(2,n)+....+f(i,n)
     * 其中序列长度为1或者n=0时 G(0)=f(1,0)=1, G(1)=f(1,1)=1
     *
     * 对于f(i,n)则可通过左子数G(i-1)和右子数G(n-i)的所有组合方式计算得出
     * f(i,n) = G(i-1)*G(n-i)
     *
     * G(n) = G(1-1)*G(n-1) + G(2-1)*G(n-2) + .... + G(i-1)*G(n-i)
     *
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int m = 2; m <= n; m++) {
            for (int i = 1; i <= m; i++) {
                G[m] += G[i - 1] * G[m - i];
            }
        }
        return G[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}