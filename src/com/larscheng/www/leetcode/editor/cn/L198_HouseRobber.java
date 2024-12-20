//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
//
// Related Topics 数组 动态规划 👍 2887 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L198_HouseRobber{
      
  public static void main(String[] args) {
       Solution solution = new L198_HouseRobber().new Solution();
       Solution2 solution2 = new L198_HouseRobber().new Solution2();
      System.out.println(solution.rob(new int[]{0}));
      solution2.rob(new int[]{2,7,9,3,1});
  }

    /**
     * 从n个房间里偷出来的最大金额f(n)
     * 1:偷前n-1个房子但最后一间不偷
     * 2:偷前n-2个房子和最后一间房子
     * f(n) = Max(f(n-1),f(n-2)+h[n-1])
     * f(0) = 0, f(1) = h[1], f(2) = Max(f(1),f(0)+h[1])
     *
     *
     *
     *
     *
     */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            //dp[2] = max(dp[1],dp[0]+nums[1])
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int cur = 0;
        int pre = 0;
        for (int num : nums) {
            int next = Math.max(cur, pre + num);
            pre = cur;
            cur = next;
        }
        return cur;
    }
}
class Solution2 {
    /**
     * 输出路径
     */
        public int[] rob(int[] nums) {
            int[][] dp = new int[nums.length][2];
            dp[0][0]=0;
            dp[0][1]=nums[0];
            dp[1][0]=nums[0];
            dp[1][1]=nums[1];
            int maxMoney=0;
            for (int i = 2; i < nums.length ; i++) {
                dp[i][0]=dp[i-1][1];//这家不偷，上家必偷
                dp[i][1]=Math.max(dp[i-2][1],dp[i-2][0])+nums[i];
                maxMoney = Math.max(dp[i][0],dp[i][1]);
            }
            List<Integer> path = new ArrayList<>();
            int flag = dp[nums.length - 1][0] == maxMoney ? 0 : 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (dp[i][flag] == maxMoney) {
                    path.add(nums[i]);
                    maxMoney -= nums[i];
                }
            }
            Collections.reverse(path);
            return path.stream().mapToInt(Integer::intValue).toArray();
        }
    }

}