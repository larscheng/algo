//给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时
//，返回 true；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
//
// 示例 2： 
//
// 
//输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= pushed.length <= 1000 
// 0 <= pushed[i] <= 1000 
// pushed 的所有元素 互不相同 
// popped.length == pushed.length 
// popped 是 pushed 的一个排列 
// 
//
// Related Topics 栈 数组 模拟 👍 427 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L946_ValidateStackSequences{
      
  public static void main(String[] args) {
       Solution solution = new L946_ValidateStackSequences().new Solution();
      System.out.println(solution.validateStackSequences(new int[]{1,2,3,4,5},new int[]{4,5,3,2,1}));
      System.out.println(solution.validateStackSequences(new int[]{1,2,3,4,5},new int[]{4,5,3,1,2}));
      System.out.println(solution.validateStackSequences(new int[]{2,1,0},new int[]{1,2,0}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //数组模拟栈，pushed作为栈，外层循环每个元素都为栈顶元素
    //O(n)/O(1)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //栈顶下标
        int top = 0;
        //popped下标
        int pop = 0;
        for (int push : pushed) {
            //栈顶元素
            pushed[top++] = push;
            //top>0表示栈内有元素,栈顶元素等于popped当前待比较元素
            while (top > 0 && pushed[top - 1] == popped[pop]) {
                top--;
                pop++;
            }
        }
        //栈为空
        return top == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //入栈出栈
        //O(n)/O(n)
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            int pop = 0;
            for (int push : pushed) {
                stack.push(push);
                while (!stack.isEmpty() && stack.peek() == popped[pop]) {
                    stack.pop();
                    pop++;
                }
            }
            return stack.isEmpty();
        }
    }
}