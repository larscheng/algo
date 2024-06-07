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
class Solution {
    /**
     * n数之和最终都可以简化成2数之和
     * a+b+c=target
     * 可以转换为a+b=target-c
     * 通过双指针在有序数组两端寻找和为target-c的两个指针位置
     * 如果左右指针元素和 > target-c ,说明右指针过大，需要左移，左移同时跳过重复值
     * 如果左右指针元素和 < target-c ,说明左指针过小，需要右移，右移同时跳过重复值
     *
     * 如果是 a+b+c+d=target,可以转换为 a+b=target-c-d
     * 此操作的前提：必须为有序数组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        return nNumSum(nums,3,0,0);
    }

    /**
     *
     * @param nums 有序数组
     * @param count 几个数
     * @param start 左指针起始位
     * @param target 几数之和
     * @return
     */
    private List<List<Integer>> nNumSum(int[] nums, int count, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int size = nums.length;
        if (size<count) {
            return result;
        }

        if (count>2) {
            //n数之和为target  a+b+c+...=target a+b=target-c-...
            for (int i = 0; i < size; i++) {
                //递归计算n-1数之和为target-nums[i]
                List<List<Integer>> temp = nNumSum(nums, count-1, i+1, target-nums[i]);
                for (List<Integer> list : temp) {
                    list.add(nums[i]);
                    result.add(list);
                }

                //如果i后面有相邻重复值，则直接跳过，避免重复结果
                while (i<size-1&&nums[i]==nums[i+1]) {
                    i++;
                }
            }

        }else{
            //2数之和为target
            int left = start;
            int right = size-1;
            while (left<right) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int sum = leftNum+rightNum;
                if (sum>target) {
                    //右指针元素太大，左移右指针 right--，左移去重
                    while (left<right&&rightNum==nums[right]) {
                        right--;
                    }
                }else if (sum<target) {
                    while (left<right&&leftNum==nums[left]) {
                        left++;
                    }
                }else{
                    //符合条件收集数据
                    result.add(new ArrayList<>(Arrays.asList(leftNum,rightNum)));
                    //移动左右指针后继续找
                    while (left<right&&rightNum==nums[right]) {
                        right--;
                    }
                    while (left<right&&leftNum==nums[left]) {
                        left++;
                    }
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    class Solution1 {
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
}