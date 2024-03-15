//给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。 
//
// 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。 
//
// 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4]
//输出: [24,12,8,6]
// 
//
// 示例 2: 
//
// 
//输入: nums = [-1,1,0,-3,3]
//输出: [0,0,9,0,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁵ 
// -30 <= nums[i] <= 30 
// 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内 
// 
//
// 
//
// 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。） 
//
// Related Topics 数组 前缀和 👍 1744 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L238_ProductOfArrayExceptSelf{
      
  public static void main(String[] args) {
       Solution solution = new L238_ProductOfArrayExceptSelf().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //左右乘积数组，使用输出数组，存储left结果，动态计算right的同时填充结果到输出数组
    //O(n)/O(1)输出数组不算在空间复杂度
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        //输出数组不算在空间复杂度
        int[] res = new int[length];
        res[0] = 1;
        //计算left，先放在res中
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        //计算right，同时计算left*right
        int right = 1;
        for (int i = length-1; i >= 0; i--) {
            //计算i的自身以外乘积 left[i]*right[i]
            res[i] = res[i] * right;
            // 计算i-1的right
            // right[i-1]=right[i]*nums[i]
            right = right * nums[i];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //左右乘积数组
        //O(n)/O(n)
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            int[] left = new int[length];
            int[] right = new int[length];
            int[] res = new int[length];

            left[0] = 1;
            right[length - 1] = 1;

            for (int i = 1; i < length; i++) {
                left[i] = left[i - 1] * nums[i - 1];
            }
            for (int i = length - 2; i >= 0; i--) {
                right[i] = right[i + 1] * nums[i + 1];
            }

            for (int i = 0; i < length; i++) {
                res[i] = left[i] * right[i];
            }
            return res;
        }
    }
}