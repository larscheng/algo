//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 2635 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L239_SlidingWindowMaximum{
      
  public static void main(String[] args) {
       Solution solution = new L239_SlidingWindowMaximum().new Solution();
       solution.maxSlidingWindow(new int[]{1,6,-1,-3,5,3,6,7},3);
  }

    /**
     * 大顶堆，数组形式记录元素下标和元素值，在大顶堆中维护一个滑动窗口，堆顶为最大值
     * 当堆中元素大于等于k时，取出堆顶元素，同时判断该元素是否在滑动窗口范围内：
     * 在窗口范围内：记录堆顶元素
     * 不在窗口范围内：弹出该元素，继续取在范围内的堆顶元素
     *
     * 判断是否在滑动窗口内：判断堆中元素的下标值是否小于窗口左端元素下标(i-k+1),i为窗口右端元素下标
     */
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            int[] result = new int[nums.length - k + 1];
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                queue.offer(new int[]{i, nums[i]});
                if (queue.size() >= k) {
                    //最大值在窗口外，则直接丢弃
                    while (queue.peek()[0] < i - k + 1) {
                        queue.poll();
                    }
                    //取出最大值，放入新数组
                    result[index++] = queue.peek()[1];
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        /**
         * 大顶堆实现需要在窗口中维护完整的k个元素，但实际上滑动过程中如果新元素大于窗口中所有原本的元素，最终的有效结果只会是新元素
         * 所以这种情况时，我们可以只保留这个最大元素在窗口中，最终滑动窗口中的元素会形成一个单调递减趋势
         * 可以通过双端队列来保存滑动窗口内的元素下标
         * 右移时判断新元素与老元素的大小，只保留大于新元素的数
         * 右移过程中维护最左侧窗口外失效元素的移除，最终取出窗口内最左端元素即可
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] result = new int[nums.length - k + 1];
            int index = 0;
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                //当前元素大于窗口内元素，清除窗口元素
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.addLast(i);
                //窗口收缩，小于左端点（i - k + 1）都移除
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
                //当前下标大于等于窗口大小时，开始收集结果
                if (i >= k - 1) {
                    result[index++] = nums[deque.peekFirst()];
                }
            }
            return result;
        }
    }
}