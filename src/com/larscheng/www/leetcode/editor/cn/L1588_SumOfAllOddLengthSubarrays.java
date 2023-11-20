//给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。 
//
// 子数组 定义为原数组中的一个连续子序列。 
//
// 请你返回 arr 中 所有奇数长度子数组的和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,4,2,5,3]
//输出：58
//解释：所有奇数长度子数组和它们的和为：
//[1] = 1
//[4] = 4
//[2] = 2
//[5] = 5
//[3] = 3
//[1,4,2] = 7
//[4,2,5] = 11
//[2,5,3] = 10
//[1,4,2,5,3] = 15
//我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58 
//
// 示例 2： 
//
// 
//输入：arr = [1,2]
//输出：3
//解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。 
//
// 示例 3： 
//
// 
//输入：arr = [10,11,12]
//输出：66
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 100 
// 1 <= arr[i] <= 1000 
// 
//
// 
//
// 进阶： 
//
// 你可以设计一个 O(n) 时间复杂度的算法解决此问题吗？ 
//
// Related Topics 数组 数学 前缀和 👍 262 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L1588_SumOfAllOddLengthSubarrays{
      
  public static void main(String[] args) {
       Solution solution = new L1588_SumOfAllOddLengthSubarrays().new Solution();
      System.out.println(solution.sumOddLengthSubarrays(new int[]{10,11,12}));

      //2,3,4,5,6,7,8,9
      //0,1,2,3,4,5,6,7
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {

        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i] + (i == 0 ? 0 : temp[i - 1]);
        }



        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
            for (int j = i + 2; j < arr.length; j += 2) {
                result += temp[j] - (i < 1 ? 0 : temp[i-1]);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        public int sumOddLengthSubarrays(int[] arr) {

            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                result += arr[i];
                for (int j = i + 2; j < arr.length; j += 2) {
                    for (int k = i; k <= j; k++) {
                        result += arr[k];
                    }
                }
            }
            return result;
        }
    }
    class Solution1 {
        public int sumOddLengthSubarrays(int[] arr) {

            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                result += arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    if (i == 0 && j % 2 == 0) {
                        int num = 0;
                        for (int k = i; k <= j; k++) {
                            num += arr[k];
                        }
                        result += num;
                    } else if (i > 0 && (j - i) % 2 == 0) {
                        int num = 0;
                        for (int k = i; k <= j; k++) {
                            num += arr[k];
                        }
                        result += num;
                    }
                }
            }
            return result;
        }
    }

}