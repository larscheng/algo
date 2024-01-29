//给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。 
//
// 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,1,3,2,5]
//输出：[3,5]
//解释：[5, 3] 也是有效的答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [-1,0]
//输出：[-1,0]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,1]
//输出：[1,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 3 * 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 除两个只出现一次的整数外，nums 中的其他数字都出现两次 
// 
//
// Related Topics 位运算 数组 👍 870 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L260_SingleNumberIii{
      
  public static void main(String[] args) {
       Solution solution = new L260_SingleNumberIii().new Solution();
       
  }

    /**
     * 异或运算特性：
     * - 相同数字异或结果为0
     * - 一个数字与0异或结果仍为这个数
     *
     * 把数组所有数进行异或，最终结果必然是仅出现一次的两个数的异或结果
     * 并且这两个数不相等，必然有一bit位为1
     * 通过 x&-x 来获取x的最低位1，遍历nums数组元素
     * 根据这最低位1对所有元素分类，同时进行异或运算，最终两个结果即为不重复的两个数字
     * O(n)/O(1)
     */
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] singleNumber(int[] nums) {
            int xorsum = 0;
            for (int num : nums) {
                xorsum ^= num;
            }
            // 防止溢出
            int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
            int type1 = 0, type2 = 0;
            for (int num : nums) {
                if ((num & lsb) != 0) {
                    type1 ^= num;
                } else {
                    type2 ^= num;
                }
            }
            return new int[]{type1, type2};
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


}