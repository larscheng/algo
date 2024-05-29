//给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//提示：
//
// 
// n == nums.length 
// 1 <= n <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
//
// Related Topics 数组 哈希表 分治 计数 排序 👍 2209 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L169_MajorityElement{
      
  public static void main(String[] args) {
       Solution solution = new L169_MajorityElement().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 投票大法，
     * 首个元素为待选值，他的票数为1，遇到相同的值，票数+1，遇到不同的值，票数-1，如果-1后票数为0，则更换待选值,重置票数为1
     * 原理：众数个数大于n/2，其他数个数必定小于n/2，众数个数和-其他数个数和>=1
     * O(n)/O(1)
     */
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count =1;
        for (int i = 1; i < nums.length; i++) {
            if (result==nums[i]){
                ++count;
            } else if (--count==0) {
                result = nums[i];
                count=1;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}