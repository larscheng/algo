//给你一个整数数组 arr 。 
//
// 将 arr 分割成若干 块 ，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。 
//
// 返回能将数组分成的最多块数？ 
//
// 示例 1： 
//
// 
//输入：arr = [5,4,3,2,1]
//输出：1
//解释：
//将数组分成2块或者更多块，都无法得到所需的结果。 
//例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。 
// 
//
// 示例 2： 
//
// 
//输入：arr = [2,1,3,4,4]
//输出：4
//解释：
//可以把它分成两块，例如 [2, 1], [3, 4, 4]。 
//然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 2000 
// 0 <= arr[i] <= 10⁸ 
// 
//
// Related Topics 栈 贪心 数组 排序 单调栈 👍 294 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L768_MaxChunksToMakeSortedIi{
      
  public static void main(String[] args) {
       Solution solution = new L768_MaxChunksToMakeSortedIi().new Solution();
       solution.maxChunksToSorted(new int[]{2,1,3,4,4});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * 思路
     * - 数组拆分 => 局部升序 => 合并所有子序列 => 与原数组升序一致
     *
     * 要保证最终合并后的序列整体升序，那么拆分出来的数组块之间，必须要为递增趋势
     * [2,1,3,4,4] 可拆分为 [2, 1], [3, 4, 4] , 第1块的中所有数组整体都小于第2块
     * [2,1,3,4,4] 可拆分为 [2, 1], [3], [4], [4], 可见数组块之间整体递增趋势
     * 结合单调栈：找下一个大于xxx、下一个小于xxx的思路，
     *
     * 保证最终留在栈内的都是每一轮寻找的最大值，栈的大小即为最大拆分块数
     *
     * 单调栈：栈内元素有序，出栈顺序升序为单调递增栈，出栈顺序降序为单调递减栈
     * 构造单调栈的过程：https://lucifer.ren/blog/2020/11/03/monotone-stack/ （推荐配合食用）
     * -
     * -
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            if (stack.isEmpty() || stack.peek() <= num){
                stack.push(num);
            }else {
                int max = stack.pop();
                while (!stack.isEmpty() && stack.peek() > num) {
                    stack.pop();
                }
                stack.push(max);
            }
        }
        return stack.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}