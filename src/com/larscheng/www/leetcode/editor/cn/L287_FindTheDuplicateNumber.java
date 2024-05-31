//给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。 
//
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。 
//
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,4,2,2]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,1,3,4,2]
//输出：3
// 
//
// 示例 3 : 
//
// 
//输入：nums = [3,3,3,3,3]
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁵ 
// nums.length == n + 1 
// 1 <= nums[i] <= n 
// nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次 
// 
//
// 
//
// 进阶： 
//
// 
// 如何证明 nums 中至少存在一个重复的数字? 
// 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？ 
// 
//
// Related Topics 位运算 数组 双指针 二分查找 👍 2404 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L287_FindTheDuplicateNumber{
      
  public static void main(String[] args) {
       Solution solution = new L287_FindTheDuplicateNumber().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 参考142环形链表2思路
     * 数组必有1个重复数字，转换成链表表示，该链表存在环
     * 下标0->nums[0] f(0)=nums[0]=1
     * 下标1->nums[1] f(1)=nums[1]=3
     * ....
     * f(nums[0])->f(1)=3
     * 如果有重复，此时就会存在环
     *
     * 求重复数字 相当于求环入口元素
     * 可以通过快慢指针求解
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        //慢指针每次1步，快指针每次2步
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * n+1个数字放在数组中，n+1个数字的范围都在【1,n】中，所以必定至少有一个重复数字
     * 通过2分法来找这个重复数字，需要一个2分条件，并且应该对值域进行2分，而不是下标索引2分
     * 值域2分，mid = （1+n）/2
     * 在原数组中小于等于mid的数字个数为count
     * 如果count>mid,则原数组中取值在【1，mid】的数个数超过了mid，说明有重复，所以重复数字在左边
     * 如果count<=mid，则重复数组在右边
     */
    public int findDuplicate(int[] nums) {
        int left = 1;
        //给n+1个数字的数字，其值域在1-n，所以n=len-1
        int right = nums.length - 1;
        while (left<right){
            int mid = (left+right)/2;
            int count =0;
            //数组中小于等于mid的元素个数
            for (int num : nums) {
                if (num<=mid){
                    count++;
                }
            }
            //一个萝卜一个坑，萝卜多了，肯定是挤在了一个坑（有重复）
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

}