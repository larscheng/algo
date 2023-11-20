//给你一个整数数组 nums 。 
//
// 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。 
//
// 返回好数对的数目。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,2,3,1,1,3]
//输出：4
//解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
// 
//
// 示例 2： 
//
// 输入：nums = [1,1,1,1]
//输出：6
//解释：数组中的每组数字都是好数对 
//
// 示例 3： 
//
// 输入：nums = [1,2,3]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 哈希表 数学 计数 👍 131 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1512_NumberOfGoodPairs{
      
  public static void main(String[] args) {
       Solution solution = new L1512_NumberOfGoodPairs().new Solution();
       solution.numIdenticalPairs(new int[]{1,2,3,1,1,3});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //O(n)/O(1)
    public int numIdenticalPairs(int[] nums) {
        int result = 0;
        int[] temp = new int[101];
        // 用数组来记录相同数字出现的次数，次数累加
        for (int num : nums) {
            result = result + temp[num - 1];
            temp[num - 1]++;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //O(n^2)/O(1)
        public int numIdenticalPairs(int[] nums) {
            int result = 0;

            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[i]==nums[j]){
                        result++;
                    }
                }
            }
            return result;
        }
    }

}