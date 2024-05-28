//给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。 
//
// 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。 
//
// 
// 
// 
// 
// 
//
// 示例 1 ： 
//
// 
//输入：nums = [2,2,1]
//输出：1
// 
//
// 示例 2 ： 
//
// 
//输入：nums = [4,1,2,1,2]
//输出：4
// 
//
// 示例 3 ： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -3 * 10⁴ <= nums[i] <= 3 * 10⁴ 
// 除了某个元素只出现一次以外，其余每个元素均出现两次。 
// 
//
// Related Topics 位运算 数组 👍 3150 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L136_SingleNumber{
      
  public static void main(String[] args) {
       Solution solution = new L136_SingleNumber().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /***
     * 异或运算
     * - 任何数与0异或运算，结果为其本身 a^0=a
     * - 任何数和其自身异或，结果为0 a^a=0
     * - 异或运算存在交换律和结合律
     *   - a^b^a=b^a^a=b^(a^a)=b^0=b
     * 数组中的所有元素一起异或运算
     * 出现两次的元素异或结果为0（相同值异或=0），最终结果即为仅出现1次的元素（a^0=a）
     *
     * O(n)/O(1)
     */
    public int singleNumber(int[] nums) {
        int result =0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}