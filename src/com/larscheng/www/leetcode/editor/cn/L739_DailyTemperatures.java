//给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现
//在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。 
//
// 
//
// 示例 1: 
//
// 
//输入: temperatures = [73,74,75,71,69,72,76,73]
//输出: [1,1,4,2,1,1,0,0]
// 
//
// 示例 2: 
//
// 
//输入: temperatures = [30,40,50,60]
//输出: [1,1,1,0]
// 
//
// 示例 3: 
//
// 
//输入: temperatures = [30,60,90]
//输出: [1,1,0] 
//
// 
//
// 提示： 
//
// 
// 1 <= temperatures.length <= 10⁵ 
// 30 <= temperatures[i] <= 100 
// 
//
// Related Topics 栈 数组 单调栈 👍 1717 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L739_DailyTemperatures{
      
  public static void main(String[] args) {
       Solution solution = new L739_DailyTemperatures().new Solution();
       solution.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            res[i] = 0;
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
         return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
      //暴力循环，O(n^2)/O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
}

}