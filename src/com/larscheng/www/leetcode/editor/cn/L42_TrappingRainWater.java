//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 5039 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L42_TrappingRainWater{
      
  public static void main(String[] args) {
       Solution solution = new L42_TrappingRainWater().new Solution();
      System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
      System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
  }

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    // 双指针，找两个指针对应的左右最小max
    // O(n)/O(1)
    public int trap(int[] height) {
        int res = 0;
        int maxLeft = 0, maxRight = 0;
        int leftPoint = 0, rightPoint = height.length - 1;
        while (leftPoint < rightPoint) {
            maxLeft = Math.max(maxLeft, height[leftPoint]);
            maxRight = Math.max(maxRight, height[rightPoint]);
            if (maxLeft < maxRight) {
                res += maxLeft - height[leftPoint++];
            } else {
                res += maxRight - height[rightPoint--];
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution3 {
        // 按列计算，关注当前列左边和右边的最高值，
        // 左右min值大于当前的值，木桶效应可得，当前列可以接的水为两者差值
        // 第1列和最后1列可忽略
        // O(n)/O(n)
        // 利用动态规划来存储历史的左右最大值
        public int trap(int[] height) {
            int res = 0;
            int[] maxLeft = new int[height.length];
            int[] maxRight = new int[height.length];
            for (int i = 1; i < height.length; i++) {
                //左侧数字值 与 左侧数字maxleft值 的最大值
                maxLeft[i] = Math.max(height[i-1], maxLeft[i-1]);
            }
            for (int i = height.length-2; i >=0; i--) {
                //右侧数字值 与 右侧数字maxright值 的最大值
                maxRight[i] = Math.max(height[i+1], maxRight[i+1]);
            }
            for (int i = 1; i < height.length-1; i++) {
                int min = Math.min(maxLeft[i],maxRight[i]);
                if (min>height[i]){
                    res+= min-height[i];
                }
            }
            return res;
        }
    }
class Solution2 {
    public int trap(int[] height) {
        int res = 0;
        // 按列计算，关注当前列左边和右边的最高值，
        // 左右min值大于当前的值，木桶效应可得，当前列可以接的水为两者差值
        // 第1列和最后1列可忽略
        // O(n^2)/O(1)
        for (int i = 1; i < height.length-1; i++) {
            int maxLeft = 0;
            for (int j = i - 1; j >= 0; j--) {
                maxLeft = Math.max(height[j], maxLeft);
            }
            int maxRight = 0;
            for (int j = i + 1; j <= height.length-1; j++) {
                maxRight = Math.max(height[j], maxRight);
            }
            int min = Math.min(maxLeft,maxRight);
            if (min>height[i]){
                res+= min-height[i];
            }
        }
        return res;
    }
}
    class Solution1 {
        //按层(按行)计算，按层数遍历，每层中当前元素小于层标准，则记为1有效
        //O(m*n)/O(1)
        public int trap(int[] height) {
            int res = 0;
            int level = Arrays.stream(height).max().getAsInt();
            for (int i = 1; i <= level; i++) {
                boolean start = false;
                int temp = 0;
                for (int item : height) {
                    if (start && item < i) {
                        temp++;
                    }
                    if (item >= i) {
                        res += temp;
                        temp = 0;
                        start = true;
                    }
                }
            }
            return res;
        }
    }

}