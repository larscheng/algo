//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。 
//
// 子数组是数组中元素的连续非空序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
//
// Related Topics 数组 哈希表 前缀和 👍 2281 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;

public class L560_SubarraySumEqualsK{
      
  public static void main(String[] args) {
       Solution solution = new L560_SubarraySumEqualsK().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //前缀和，存入map中，并记录前缀和出现次数
      // 遍历数组计算每个元素的前缀和
    // 同时检查map中是否有历史前缀和值等于sum-k，如果存在说明有若干个下标到当前位置的元素和为k

    //O(n)/O(n)
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            //从0到i，累加为k，记录一次有效序列
            if (sum == k) {
                res++;
            }
            //map中存在前缀和满足sum-k
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    //遍历截止第i个元素，统计他之前的子数组和为k的个数
    //O(n^2)/O(n)
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }
}

}