//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
//
// Related Topics 数组 动态规划 👍 1152 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L118_PascalsTriangle{
      
  public static void main(String[] args) {
       Solution solution = new L118_PascalsTriangle().new Solution();
      System.out.println(solution.generate(4));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //1
    //1，1
    //1，2，1
    //1，3，3，1
    //根据规律遍历计算，第n行第i个元素表示为n[i],n[i]=n-1[i-1]+n-1[i]
    //特殊情况：i=0或者i=n-1时，即每行第1和最后一个元素值==1
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        int[][] dp = new int[numRows][numRows];
        dp[0][0]=1;
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j==0){
                    dp[i][j]=1;
                }else {
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }
                temp.add(dp[i][j]);
            }
            res.add(temp);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //1
        //1，1
        //1，2，1
        //1，3，3，1
        //根据规律遍历计算，第n行第i个元素表示为n[i],n[i]=n-1[i-1]+n-1[i]
        //特殊情况：i=0或者i=n-1时，即每行第1和最后一个元素值==1
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        temp.add(1);
                    } else {
                        temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                    }
                }
                res.add(temp);
            }
            return res;
        }
    }
}