//给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右轮转 1 步: [7,1,2,3,4,5,6]
//向右轮转 2 步: [6,7,1,2,3,4,5]
//向右轮转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右轮转 1 步: [99,-1,-100,3]
//向右轮转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 0 <= k <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// Related Topics 数组 数学 双指针 👍 2116 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L189_RotateArray{
      
  public static void main(String[] args) {
       Solution solution = new L189_RotateArray().new Solution();
       solution.rotate(new int[]{1,2,3,4,5,6,7},3);
       solution.rotate(new int[]{1,2,3,4,5,6,7},10);
       solution.rotate(new int[]{-1,-100,3,99},2);
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      // O(n)/O(1) 数组反转后，按k值分为两部分，分别进行反转
    public void rotate(int[] nums, int k) {
        //1,2,3,4,5,6,7
        k = k % nums.length;
        //7,6,5,4,3,2,1
        reverse(nums, 0, nums.length-1);
        //5,6,7,4,3,2,1
        reverse(nums, 0, k - 1);
        //5,6,7,1,2,3,4
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    // O(n)/O(n) 取余数为下标
    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[(k + i) % nums.length] = nums[i];
        }
        System.arraycopy(res, 0, nums, 0, nums.length);
    }
}
    class Solution1 {
        public void rotate(int[] nums, int k) {
            if (k == nums.length || k == 0) {
                return;
            }
            if (k > nums.length) {
                k = k % nums.length;
            }
            List<Integer> res = new ArrayList<>();

            for (int i = nums.length - k; i < nums.length; i++) {
                res.add(nums[i]);
            }
            for (int i = 0; i < nums.length - k; i++) {
                res.add(nums[i]);
            }
            System.arraycopy(res.stream().mapToInt(Integer::valueOf).toArray(), 0, nums, 0, nums.length);
            //System.out.println(Arrays.toString(nums));
        }
    }
}