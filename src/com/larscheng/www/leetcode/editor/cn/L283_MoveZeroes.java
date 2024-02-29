//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 
//
// 示例 2: 
//
// 
//输入: nums = [0]
//输出: [0] 
//
// 
//
// 提示: 
// 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能尽量减少完成的操作次数吗？ 
//
// Related Topics 数组 双指针 👍 2308 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L283_MoveZeroes{
      
  public static void main(String[] args) {
       Solution solution = new L283_MoveZeroes().new Solution();
       solution.moveZeroes(new int[]{0,1,0,0,3,12});

  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        //不为0的数，可交换的下标位置
        int point = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素不为0，则交换元素
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[point];
                nums[point] = temp;
                point++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public void moveZeroes(int[] nums) {
        int point = 0;
        //把所有非0的数放一起，剩下的位子全部赋值为0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[point] = nums[i];
                point++;
            }
        }
        for (int i = point; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

}