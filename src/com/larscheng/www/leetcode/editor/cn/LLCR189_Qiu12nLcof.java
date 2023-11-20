//请设计一个机械累加器，计算从 1、2... 一直累加到目标数值 target 的总和。注意这是一个只能进行加法操作的程序，不具备乘除、if-else、
//switch-case、for 循环、while 循环，及条件判断语句等高级功能。 
//
// 
//
// 示例 1： 
//
// 
//输入: target = 5
//输出: 15
// 
//
// 示例 2： 
//
// 
//输入: target = 7
//输出: 28
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 10000 
// 
//
// 
//
// Related Topics 位运算 递归 脑筋急转弯 👍 640 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class LLCR189_Qiu12nLcof{
      
  public static void main(String[] args) {
       Solution solution = new LLCR189_Qiu12nLcof().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    private int sum = 0;
    public int mechanicalAccumulator(int target) {
        //通过短路且,中断递归
        boolean b = target > 1 && mechanicalAccumulator(target - 1) > 0;
        sum += target;
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int mechanicalAccumulator(int target) {
        return target == 1 ? 1 : target + mechanicalAccumulator(target - 1);
    }
}
class Solution2 {
    private int sum = 0;
    public int mechanicalAccumulator(int target) {
        //通过短路且,中断递归
        boolean b = target > 1 && mechanicalAccumulator(target - 1) > 0;
        sum += target;
        return sum;
    }
}

}