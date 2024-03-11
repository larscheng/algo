//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
//
// Related Topics 栈 数组 单调栈 👍 2680 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class L84_LargestRectangleInHistogram{
      
  public static void main(String[] args) {
       Solution solution = new L84_LargestRectangleInHistogram().new Solution();
      //System.out.println(solution.largestRectangleArea(new int[]{2,1,5,6,2,3}));
      //System.out.println(solution.largestRectangleArea(new int[]{2,4}));
      System.out.println(solution.largestRectangleArea(new int[]{2,1,2}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        //单调栈，存储元素值递增的下标值
        Stack<Integer> stack = new Stack<>();
        //原数组首尾增加哨兵，保证单调栈元素顺利入栈和弹出
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);

        for (int i = 0; i < newHeights.length; i++) {
            //检查栈顶元素对应的左右边界位置，记录矩形面积最大值
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int cur = stack.pop();
                int width = i - stack.peek() - 1;
                res = Math.max(res, width * newHeights[cur]);
            }
            stack.push(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
      //暴力解法
    //每个元素作为起点，遍历取剩余元素，计算组合成有效矩形的面积，并收集最大值
    //O(n^2)/O(1)
    public int largestRectangleArea(int[] heights) {
        int res=0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(heights[j], minHeight);
                res = Math.max(res, minHeight * (j - i + 1));
            }
        }
        return res;
    }
}

}