//按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。 
//
// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
// 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// AQAA
// AAAQ
// QAAA
// AAQA
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
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
// Related Topics 数组 回溯 👍 2069 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L51_NQueens{
      
  public static void main(String[] args) {
       Solution solution = new L51_NQueens().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        //记录每行皇后所在的列
        int[] queenIndex  = new int[n];
        Arrays.fill(queenIndex,-1);
        Set<Integer> col = new HashSet<>();
        //左上到右下（差恒等）
        Set<Integer> xieXian1 = new HashSet<>();
        //右上到左下（和恒等）
        Set<Integer> xieXian2 = new HashSet<>();

        backtrack(result,queenIndex,n,0,col,xieXian1,xieXian2);



        return result;
    }

    private void backtrack(List<List<String>> result, int[] queenIndex, int n, int row, Set<Integer> col, Set<Integer> xieXian1, Set<Integer> xieXian2) {
        if (row==n){
            //根据queens数组转化为结果集
            result.add(createResult(queenIndex,n));
        }else {
            for (int i = 0; i < n; i++) {
                if (col.contains(i)){
                    continue;
                }
                int diff = row - i;
                if (xieXian1.contains(diff)){
                    continue;
                }
                int sum = row+i;
                if (xieXian2.contains(sum)){
                    continue;
                }
                queenIndex[row] = i;
                col.add(i);
                xieXian1.add(diff);
                xieXian2.add(sum);
                //递归
                backtrack(result,queenIndex,n,row+1,col,xieXian1,xieXian2);
                //重置
                queenIndex[row] = -1;
                col.remove(i);
                xieXian1.remove(diff);
                xieXian2.remove(sum);
            }
        }
    }

    private List<String> createResult(int[] queenIndex, int n) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row,'.');
            row[queenIndex[i]]='Q';
            temp.add(new String(row));
        }
        return temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}