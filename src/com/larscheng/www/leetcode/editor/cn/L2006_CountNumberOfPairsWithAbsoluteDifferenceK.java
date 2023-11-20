//给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
// 
//
// |x| 的值定义为： 
//
// 
// 如果 x >= 0 ，那么值为 x 。 
// 如果 x < 0 ，那么值为 -x 。 
// 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,2,2,1], k = 1
//输出：4
//解释：差的绝对值为 1 的数对为：
//- [1,2,2,1]
//- [1,2,2,1]
//- [1,2,2,1]
//- [1,2,2,1]
// 
//
// 示例 2： 
//
// 输入：nums = [1,3], k = 3
//输出：0
//解释：没有任何数对差的绝对值为 3 。
// 
//
// 示例 3： 
//
// 输入：nums = [3,2,1,5,4], k = 2
//输出：3
//解释：差的绝对值为 2 的数对为：
//- [3,2,1,5,4]
//- [3,2,1,5,4]
//- [3,2,1,5,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 1 <= k <= 99 
// 
//
// Related Topics 数组 哈希表 计数 👍 99 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L2006_CountNumberOfPairsWithAbsoluteDifferenceK{
      
  public static void main(String[] args) {
       Solution solution = new L2006_CountNumberOfPairsWithAbsoluteDifferenceK().new Solution();
      System.out.println(solution.countKDifference(new int[]{1, 2, 2, 1}, 1));
  }

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public int countKDifference(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int num : nums) {
            res += cnt.getOrDefault(num - k, 0) + cnt.getOrDefault(num + k, 0);
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    public int countKDifference(int[] nums, int k) {
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            // ∣nums[i]−nums[j]∣=k 的 i 的个数，亦即满足 nums[i]=nums[j]+k 或 nums[i]=nums[j]−k  的 i 的个数
            int n1 = num - k;
            int n2 = num + k;
            if (map.containsKey(n1)) {
                count += map.get(n1);
            }
            if (map.containsKey(n2)) {
                count += map.get(n2);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }
}
    class Solution1 {
        public int countKDifference(int[] nums, int k) {
            int count = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (i < j && Math.abs(nums[i] - nums[j]) == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

}