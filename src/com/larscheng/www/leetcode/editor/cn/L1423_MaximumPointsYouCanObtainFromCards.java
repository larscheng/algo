//几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。 
//
// 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。 
//
// 你的点数就是你拿到手中的所有卡牌的点数之和。 
//
// 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。 
//
// 
//
// 示例 1： 
//
// 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
//输出：12
//解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5
// = 12 。
// 
//
// 示例 2： 
//
// 输入：cardPoints = [2,2,2], k = 2
//输出：4
//解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
// 
//
// 示例 3： 
//
// 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
//输出：55
//解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
// 
//
// 示例 4： 
//
// 输入：cardPoints = [1,1000,1], k = 1
//输出：1
//解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。 
// 
//
// 示例 5： 
//
// 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
//输出：202
// 
//
// 
//
// 提示： 
//
// 
// 1 <= cardPoints.length <= 10^5 
// 1 <= cardPoints[i] <= 10^4 
// 1 <= k <= cardPoints.length 
// 
//
// Related Topics 数组 前缀和 滑动窗口 👍 379 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L1423_MaximumPointsYouCanObtainFromCards{
      
  public static void main(String[] args) {
       Solution solution = new L1423_MaximumPointsYouCanObtainFromCards().new Solution();
       
  }

    /**
     * 通过求剩余卡牌的最小值，来计算拿走卡牌的最大值
     * 剩余的卡牌是长度为n-k的连续字符串，可以通过固定长度的滑动窗口来获取
     */
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxScore(int[] cardPoints, int k) {
            int window = 0;
            int windowSize = cardPoints.length - k;
            for (int i = 0; i < windowSize; i++) {
                window += cardPoints[i];
            }
            int windowMin = window;
            for (int i = windowSize; i < cardPoints.length; i++) {
                //窗口右侧新元素 i ，窗口左侧淘汰元素 i-windowSize
                window = window + cardPoints[i] - cardPoints[i - windowSize];
                windowMin = Math.min(windowMin, window);
            }
            return Arrays.stream(cardPoints).sum() - windowMin;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}