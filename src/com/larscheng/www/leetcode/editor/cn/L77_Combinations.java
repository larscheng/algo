//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
//
// Related Topics 回溯 👍 1663 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L77_Combinations{
      
  public static void main(String[] args) {
       Solution solution = new L77_Combinations().new Solution();
      System.out.println(solution.combine(3, 2));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> trace  = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrace(n,1,k);
        return res;
    }

    private void backtrace(int n, int start, int k) {
        if (trace.size()==k){
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = start; i <= n; i++) {
            trace.addLast(i);

            backtrace(n,i+1,k);

            trace.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}