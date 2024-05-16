//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 1972 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L416_PartitionEqualSubsetSum{
    /**
     * 数量小于2、总和不能平分、最大值大于平均数 都不可拆分
     *
     * dp[i][target] dp[i][j]表示从数组nums[0:i]内选取若干数的和为是否为j（每个数只能用1次：0-1背包问题）
     * 选取的和为0的情况必定存在，dp[i][0]=true
     * 待选取的数组只有nums[0]1个元素时，dp[0][nums[0]]=true
     *
     * nums[i]选不选的问题
     * 不选择：如果在nums[0:i-1]中已有一部分元素和为j，则dp[i][j]=dp[i-1][j]
     * 若选择：那就要看nums[0:i-1]中和为j-nums[i]的状态，即dp[i-1][j-nums[i]]，所以需要j>=nums[i]
     *
     * 例如[1,2,3,6] target=6
     *       0     1      2      3      4       5     6
     * 1    true  true  false  false  false  false  false
     * 2    true  true  true   true   false  false  false
     * 3    true  true  true   true   true   true   true
     * 6    true  true  true   true   true   true   true
     *
     *
     */
    public static void main(String[] args) {
       Solution solution = new L416_PartitionEqualSubsetSum().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[len][target + 1];
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len - 1][target];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}