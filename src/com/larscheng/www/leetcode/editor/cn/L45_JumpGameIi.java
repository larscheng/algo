//给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。 
//
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处: 
//
// 
// 0 <= j <= nums[i] 
// i + j < n 
// 
//
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 1000 
// 题目保证可以到达 nums[n-1] 
// 
//
// Related Topics 贪心 数组 动态规划 👍 2514 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L45_JumpGameIi{
      
  public static void main(String[] args) {
       Solution solution = new L45_JumpGameIi().new Solution();
      System.out.println(solution.jump(new int[]{2,3,1,1,4,2,1}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 贪心算法，正向搜索，复杂度O(n)
     * 假设从首个位置出发，每次选择可以跳的更远的跳点
     * 比如2，3，1，1，4，2，1
     * 从2出发，可以跳3，1，优先选择3，因为从3可以跳的更远
     * 从3出发，可以跳1，1，4，优先选择4，因为从3可以跳的更远
     *
     */
    public int jump(int[] nums) {
        int end=0;
        int maxIndex = 0;
        int steps = 0;
        for (int i = 0; i < nums.length-1; i++) {
            maxIndex = Math.max(maxIndex,nums[i]+i);
            if (i == end) {
                end = maxIndex;
                steps++;
            }
        }
        return steps;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    /**
     * 贪心算法，逆向搜索，复杂度O(n*n)
     * 要到达最后一个位置，就找他前一个位置
     * 贪心思路遍历数组找里它最远的那个可到达位置，然后继续找上上个位置，直到位置0
     * O(n*n)/O(1)
     */
    public int jump(int[] nums) {
        int lastIndex = nums.length-1;
        int steps=0;
        //从最后1个位置开始，找跳跃次数最少的上一个起跳点
        while (lastIndex!=0){
            //从左向右找，首个符合条件的即为跳跃次数最少的那个
            for (int i = 0; i < lastIndex; i++) {
                if (nums[i] + i >= lastIndex) {
                    lastIndex = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
    class Solution1 {
        /**
         * 反向动态规划
         * dp[i] 表示 到达nums[i]的最小跳跃次数
         * i-1可以跳到i时，次数为dp[i-1]+1
         * i-2可以跳到i时，次数为dp[i-2]+1
         * ...
         * 0可以跳到i时，次数为dp[0]+1
         * dp[i] = min(dp[i-1]+1,dp[i-2]+1,...dp[0]+1)
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = 0;
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[j] + j >= i) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
            return dp[nums.length-1];
        }
    }
}