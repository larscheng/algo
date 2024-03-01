//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请 
//
// 你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 6721 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L15_ThreeSum{
      
  public static void main(String[] args) {
       Solution solution = new L15_ThreeSum().new Solution();
      //System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
      System.out.println(solution.threeSum(new int[]{-2,0,1,1,2}));
  }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            //数组排序后，重复数字在一起，遍历过程中可以直接跳过重复数字
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                //最小数大于0，则不会有满足要求的三元组
                if (nums[i] > 0) {
                    break;
                }
                //重复数字直接跳过，避免重复结果
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                //双指针
                int target = -nums[i];
                int p1 = i + 1;
                int p2 = nums.length - 1;
                while (p1 < p2) {
                    //指针移动后，判断是否与上一个数重复，避免重复结果
                    if (p1 > i + 1 && nums[p1] == nums[p1 - 1]) {
                        p1++;
                        continue;
                    }
                    if (p2 < nums.length - 1 && nums[p2] == nums[p2 + 1]) {
                        p2--;
                        continue;
                    }

                    if (nums[p1] + nums[p2] == target) {
                        //符合条件，收集结果，指针移动
                        lists.add(new ArrayList<>(Arrays.asList(nums[i], nums[p1], nums[p2])));
                        p1++;
                        p2--;
                    } else if (nums[p1] + nums[p2] > target) {
                        //p2数字过大，尝试左移p2
                        p2--;
                    } else {
                        //p1数字过小，尝试右移p1
                        p1++;
                    }
                }
            }
            return lists;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}