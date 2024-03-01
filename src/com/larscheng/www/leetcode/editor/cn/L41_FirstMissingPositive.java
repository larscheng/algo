//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 请你实现时间复杂度为 
//O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// Related Topics 数组 哈希表 👍 1968 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L41_FirstMissingPositive{
      
  public static void main(String[] args) {
       Solution solution = new L41_FirstMissingPositive().new Solution();
//      System.out.println(solution.firstMissingPositive(new int[]{1,2,2,1,3,1,0,4,0}));
//      System.out.println(solution.firstMissingPositive(new int[]{5,6,7,8,9,10,11}));
      System.out.println(solution.firstMissingPositive(new int[]{3,4,-1,1}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //1应该放在下标0，2应该放在下标1，nums[i]应该放在下标nums[i]-1
            while (nums[i] >= 1 &&
                    nums[i] <= nums.length &&
                    nums[i] != nums[nums[i] - 1]) {
                //交换完当前位置元素仍可能位置不正确，需要继续交换
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        //首个元素应该为最小正整数1，依次递增，否则即为缺失的最小正整数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 &&
                    nums[i] <= nums.length &&
                    nums[i] != nums[nums[i] - 1]) {
                // 因为可能不止一次操作，所以此处不用 if ，而改用while
                check(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }

    public void check(int[] nums, int index1, int index2) {
        // 互换位置
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
    class Solution1 {
        public int firstMissingPositive(int[] nums) {
            Arrays.sort(nums);
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i],i);
            }
            int temp = 1;
            List<Integer> list = (List<Integer>) map.keySet().stream().sorted();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > 0 && temp == list.get(i)) {
                    temp++;
                }else if (list.get(i) > 0 && temp != list.get(i)){
                    return temp;
                }
            }
            return temp;
        }
    }

}