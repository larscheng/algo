//给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。 
//
// 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。 
//
// 以数组形式返回答案。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [8,1,2,2,3]
//输出：[4,0,1,1,3]
//解释： 
//对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
//对于 nums[1]=1 不存在比它小的数字。
//对于 nums[2]=2 存在一个比它小的数字：（1）。 
//对于 nums[3]=2 存在一个比它小的数字：（1）。 
//对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
// 
//
// 示例 2： 
//
// 输入：nums = [6,5,4,8]
//输出：[2,1,0,3]
// 
//
// 示例 3： 
//
// 输入：nums = [7,7,7,7]
//输出：[0,0,0,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 500 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics 数组 哈希表 计数 排序 👍 273 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;

public class L1365_HowManyNumbersAreSmallerThanTheCurrentNumber {

public static void main(String[] args) {
    Solution solution = new L1365_HowManyNumbersAreSmallerThanTheCurrentNumber().new Solution();
    System.out.println(Arrays.toString(solution.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 时间复杂度O(n) 空间复杂度O(1)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        //8,1,2,2,3
        int[] temp = new int[101];
        //计数，每个数字的个数,1个1，2个2，1个3，1个8
        //0,1,2,3,4,5,6,7,8,9,10....
        //0,1,2,1,0,0,0,0,1,0,0....
        for (int n : nums) {
            temp[n]++;
        }
        //计数，每个数字之前有几个数字，temp[i]前面有x个数字比他小（前缀和）
        //0,1,2,3,4,5,6,7,8,9,10....
        //0,1,3,4,4,4,4,4,5,5,5....
        for (int i = 1; i < temp.length; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result[i] = temp[nums[i] - 1];
            }
        }
        return result;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        // 时间复杂度O(n) 空间复杂度O(1)
        public int[] smallerNumbersThanCurrent(int[] nums) {
            //8,1,2,2,3
            int[] temp = Arrays.copyOf(nums, nums.length);
            //排序，下标代表小于当前数的个数
            Arrays.sort(temp);

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < temp.length; i++) {
                //重复不收集，数字对应的下标
                if (!map.containsKey(temp[i])) {
                    map.put(temp[i], i);
                }
            }
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                result[i] = map.get(nums[i]);
            }
            return result;
        }
    }

class Solution1 {
    // 时间复杂度O(n^2) 空间复杂度O(n)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] > nums[j]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }
}

}