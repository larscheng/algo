//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// Related Topics 并查集 数组 哈希表 👍 1983 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashSet;
import java.util.Set;

public class L128_LongestConsecutiveSequence{
      
  public static void main(String[] args) {
       Solution solution = new L128_LongestConsecutiveSequence().new Solution();
      System.out.println(solution.longestConsecutive(new int[]{100,4,200,1,3,2}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 哈希去重，遍历去重后的元素
     * 若当前元素num在集合中无前置数字，不存在num-1
     * 则以num为起点，开始+1循环检查是否存在连续数字，每找到一个长度+1
     * 第一层循环只有不存在num-1的数能进入，内层循环只有连续数字能够进入，所以复杂度为O(n)
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        //去重
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        for (int num : set) {
            //以元素(没有前置数字)为起点，+1检查是否有连续数字
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int length = 1;

                while (set.contains(++currentNum)) {
                    length++;
                }
                res = Math.max(res, length);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}