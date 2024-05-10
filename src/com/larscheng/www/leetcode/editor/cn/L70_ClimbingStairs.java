//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 3512 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

public class L70_ClimbingStairs{
      
  public static void main(String[] args) {
       Solution solution = new L70_ClimbingStairs().new Solution();
      System.out.println(solution.climbStairs(100));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //动态规划中发现，每次关注的都是n-1和n-2，可以进行空间优化
    //利用滚动变量取代dp数组
    //O(n)/O(1)
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int x = 1, y = 2;
        int sum;
        for (int i = 3; i <= n; i++) {
            sum = x + y;
            x = y;
            y = sum;
        }
        return y;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution3 {
        //纯递归的优化点：存在重复计算 n=n-1+n-2 n-1=n-2+n-3  n-2就多算了一次
        //已知fn=fn-1+fn-2，可以通过动态规划，临界值n=1和n=2
        //O(n)/O(n)
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n+1];
            dp[1]=1;
            dp[2]=2;
            for (int i = 3; i <= n; i++) {
                dp[i]= dp[i-1]+dp[i-2];
            }

            return dp[n];
        }
    }
    class Solution2 {
        //纯递归的优化点：存在重复计算 n=n-1+n-2 n-1=n-2+n-3  n-2就多算了一次
        //用哈希表记录算过的结果，进行记忆化递归
        //O(n)/O(n)
        Map<Integer,Integer> mem = new HashMap<>();
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            if (mem.containsKey(n)){
                return mem.get(n);
            }

            int fn = climbStairs(n - 1) + climbStairs(n - 2);
            mem.put(n, fn);

            return fn;
        }
    }
    class Solution1 {
        //从n反过来看，从n-1级和n-2级都可以到达n级楼梯，所以f(n)=f(n-1)+f(n-2)
        //再看特殊case，n=1和n=2
        //递归计算f(n)
        //O(n*n)/O(1)
        //优化点：存在重复计算 n=n-1+n-2 n-1=n-2+n-3  n-2就多算了一次
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }
}