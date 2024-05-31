//整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。 
//
// 
// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。 
// 
//
// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就
//是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。 
//
// 
// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 
// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。 
// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。 
// 
//
// 给你一个整数数组 nums ，找出 nums 的下一个排列。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics 数组 双指针 👍 2497 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L31_NextPermutation{
      
  public static void main(String[] args) {
       Solution solution = new L31_NextPermutation().new Solution();
       solution.nextPermutation(new int[]{1,2,3});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 从后往前找第一个相邻升序的元素对(i,j)，nums[i]<nums[j]
     * 此时j往后的元素均为降序，[j,end]
     * 要找的下一个排列，要比够距离当前足够小，并大于当前数列
     * 需要从[j,end]中找第一个满足nums[k]>nums[i]的k元素与i交换位置
     * 交换后，[j,end]的元素要调整为升序，保证足够小切大于当前数列
     * 如果最开始找不到元素对(i,j)，nums[i]<nums[j]，则直接将所有元素调整为升序
     * https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/?envType=study-plan-v2&envId=top-100-liked
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int i = length - 1;
        int j = length - 1;
        //找相邻升序对 nums[i]<nums[j]
        for (int k = length-2; k >=0; k--) {
            if (nums[k]<nums[k+1]){
                i = k;
                j = k+1;
                break;
            }
        }
        if (i==j){
            reverse(nums, 0, length-1);
        }
        //从[j,end]中 由后向前找一个大于nums[i]的数
        for (int k = length-1; k >=j ; k--) {
            if (nums[k]>nums[i]){
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
                break;
            }
        }
        //[j,end]为降序，下一个排列必须足够小，所以要将[j,end]转为升序
        reverse(nums, j, length-1);

    }

    private void reverse(int[] nums, int left, int right) {
        while (left <right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}