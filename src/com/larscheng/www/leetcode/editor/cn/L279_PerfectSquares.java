//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数学 动态规划 👍 1958 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L279_PerfectSquares{
      
  public static void main(String[] args) {
       Solution solution = new L279_PerfectSquares().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //n=12，从1开始用平方数凑出12
    //从1开始，11分解成平方和所需要的最小个数为n1,总的最小个数n1+1
    //从4开始，8分解成平方和所需要的最小个数为n2,总的最小个数n2+1
    //从9开始，3分解成平方和所需要的最小个数为n3,总的最小个数n3+1
    //最小个数：Math.min(n1+1,n2+1,n3+1)
    //11，8，3的算法如上，所以可以使用记忆化递归
    //有了上面的铺垫，可以改写成动态规划，dp[i]表示凑出总和为i的平方数最小个数
    //dp[i] = min(dp[i],dp[i-j]+1)), dp[i]默认值为i，j为平方数
    public int numSquares(int n) {

        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        //从1，2，3，4。。。开始凑数，已知到n
        for (int i = 1; i <= n; i++) {
            //用1、4、9....平方数挨个凑
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    //n=12，从1开始用平方数凑出12
    //从1开始，11分解成平方和所需要的最小个数为n1,总的最小个数n1+1
    //从4开始，8分解成平方和所需要的最小个数为n2,总的最小个数n2+1
    //从9开始，3分解成平方和所需要的最小个数为n3,总的最小个数n3+1
    //最小个数：Math.min(n1+1,n2+1,n3+1)
    //11，8，3的算法如上，所以可以使用记忆化递归
    Map<Integer,Integer> map  = new HashMap<>();
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        if (map.containsKey(n)){
            return map.get(n);
        }
        int count = n;
        for (int i = 1; i * i <= n; i++) {
            count = Math.min(count, numSquares(n - i * i) + 1);
        }
        map.put(n,count);
        return count;
    }
}

}