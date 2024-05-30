//给定一个包含红色、白色和蓝色、共 n 个元素的数组
// nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 必须在不使用库内置的 sort 函数的情况下解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
//
// Related Topics 数组 双指针 排序 👍 1782 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L75_SortColors{
      
  public static void main(String[] args) {
       Solution solution = new L75_SortColors().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 双指针，
     * p0表示每次交换0要放的位置,默认为0
     * p2表示每次交换2要放的位置，默认为length-1
     * 遇到0直接与p0交换，p0+1
     * 遇到2直接与p2交换，p2-1，但此时被交换过来的元素有可能还是2，所以需要循环处理，直到被交换过来的元素不是2
     * 被交换过来的如果是2，则继续交换
     * 被交换过来的如果是0，则与p0交换
     * 被交换过来的如果是1，则无需调整
     * 所以2的处理逻辑要先于0，否则会漏处理
     * O(n)/O(1)
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 =0,p2=n-1;
        for (int i = 0; i <= p2; i++) {
            //被交换过来的元素有可能也是2
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i]==0){
                int temp = nums[i];
                nums[i]=nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * 双指针，p0表示每次交换0要放的位置，p1表示每次交换1要放的位置，默认为0
     * 要求按照0，1，2，所以在交换完0之后，如果p0小于p1，说明p0处的1已经被交换到nums[i]了，此时需要再放回p1处
     * O(n)/O(1)
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 =0,p1=0;
        for (int i = 0; i < n; i++) {
            if (nums[i]==1){
                int temp = nums[i];
                nums[i]=nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i]==0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                //把nums[i]的0与p0交换后，如果p0小于p1，说明在此前已经有1放在了p0处
                //此时需要把nums[i]与p1交换，重新把这个1放到前面
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }
}

}