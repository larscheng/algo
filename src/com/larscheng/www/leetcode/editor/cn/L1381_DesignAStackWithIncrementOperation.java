//请你设计一个支持对其元素进行增量操作的栈。 
//
// 实现自定义栈类 CustomStack ： 
//
// 
// CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量。 
// void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。 
// int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。 
// void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。 
//
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["CustomStack","push","push","pop","push","push","push","increment",
//"increment","pop","pop","pop","pop"]
//[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
//输出：
//[null,null,null,2,null,null,null,null,null,103,202,201,-1]
//解释：
//CustomStack stk = new CustomStack(3); // 栈是空的 []
//stk.push(1);                          // 栈变为 [1]
//stk.push(2);                          // 栈变为 [1, 2]
//stk.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
//stk.push(2);                          // 栈变为 [1, 2]
//stk.push(3);                          // 栈变为 [1, 2, 3]
//stk.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
//stk.increment(5, 100);                // 栈变为 [101, 102, 103]
//stk.increment(2, 100);                // 栈变为 [201, 202, 103]
//stk.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
//stk.pop();                            // 返回 202 --> 返回栈顶值 202，栈变为 [201]
//stk.pop();                            // 返回 201 --> 返回栈顶值 201，栈变为 []
//stk.pop();                            // 返回 -1 --> 栈为空，返回 -1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= maxSize, x, k <= 1000 
// 0 <= val <= 100 
// 每种方法 increment，push 以及 pop 分别最多调用 1000 次 
// 
//
// Related Topics 栈 设计 数组 👍 96 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1381_DesignAStackWithIncrementOperation{
      
  public static void main(String[] args) {
      L1381_DesignAStackWithIncrementOperation solution = new L1381_DesignAStackWithIncrementOperation();
        solution.test();
  }
  public void test() {
      CustomStack stack = new CustomStack(3);
      stack.push(1);
      stack.push(2);
      stack.pop();
      stack.push(2);
      stack.push(3);
      stack.push(4);
      stack.increment(5,100);
      stack.increment(2,100);
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
  }


//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 变量top记录当前栈顶位
     * push：判断top是否超过栈长度，未超过则可以入栈，入栈后更新top
     * pop：判断top是否为初始位置，如果不为初始位，返回栈顶元素
     * increment：对栈底前k位元素进行操作
     */
    class CustomStack {
    int[] stack;
    int top = 0;
    public CustomStack(int maxSize) {
        stack = new int[maxSize];
    }
    
    public void push(int x) {
        if (top<=stack.length-1){
            stack[top] = x;
            top++;
        }
    }
    
    public int pop() {
        if (top==0){
            return -1;
        }
        return stack[--top];
    }
    
    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k,top); i++) {
            stack[i]+=val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
//leetcode submit region end(Prohibit modification and deletion)


}