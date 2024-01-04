//n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
// 
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
// 1 <= n <= 9 
// 
//
// Related Topics 回溯 👍 494 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L52_NQueensIi{
      
  public static void main(String[] args) {
       Solution solution = new L52_NQueensIi().new Solution();
      System.out.println(solution.totalNQueens(3));
  }

    /**
     *
     * 皇后之间不能同行、同列、同斜线
     * 所以要记录已经放置皇后的行号、列号、斜线号（同一斜线的x-y值相同）
     *
     * 按行递归，遍历行内每个元素，寻找可放置的位置，并记录，当行号=n时结束
     *
     *
     */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        //已放皇冠的列
        Set<Integer> col = new HashSet<>();
        //已放皇后的左斜线(左上角->右下角，row-col值固定)
        Set<Integer> line1 = new HashSet<>();
        //已放皇后的右斜线(右上角->左下角，row+col值固定)
        Set<Integer> line2 = new HashSet<>();

        public int totalNQueens(int n) {
            return backtrace(n, 0);
        }

        private int backtrace(int n, int row) {
            if (row == n) {
                //递归结束，n个皇后都放完了，记录1次有效解决方案
                return 1;
            }else {
                int count = 0;
                //检查当前行每一列可放置皇后的位置
                for (int i = 0; i < n; i++) {
                    int line1Num = row-i;
                    int line2Num = row+i;
                    if (col.contains(i) || line1.contains(line1Num) || line2.contains(line2Num)) {
                        continue;
                    }
                    col.add(i);
                    line1.add(line1Num);
                    line2.add(line2Num);

                    //递归：检查下一行可放置的位置，并记录
                    count += backtrace(n, row + 1);

                    //撤销回溯记录
                    col.remove(i);
                    line1.remove(line1Num);
                    line2.remove(line2Num);
                }
                return count;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}