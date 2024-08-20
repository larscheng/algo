//请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
//
// 函数 myAtoi(string s) 的算法如下：
//
//
// 空格：读入字符串并丢弃无用的前导空格（" "）
// 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
// 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
// 舍入：如果整数数超过 32 位有符号整数范围 [−2³¹, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2³¹ 的整数应
//该被舍入为 −2³¹ ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
//
//
// 返回整数作为最终结果。
//
//
//
// 示例 1：
//
//
// 输入：s = "42"
//
//
// 输出：42
//
// 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
//
//
//带下划线线的字符是所读的内容，插入符号是当前读入位置。
//第 1 步："42"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："42"（读入 "42"）
//           ^
//
//
//
// 示例 2：
//
//
// 输入：s = " -042"
//
//
// 输出：-42
//
// 解释：
//
//
//第 1 步："   -042"（读入前导空格，但忽视掉）
//            ^
//第 2 步："   -042"（读入 '-' 字符，所以结果应该是负数）
//             ^
//第 3 步："   -042"（读入 "042"，在结果中忽略前导零）
//               ^
//
//
//
// 示例 3：
//
//
// 输入：s = "1337c0d3"
//
//
// 输出：1337
//
// 解释：
//
//
//第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
//             ^
//
//
//
// 示例 4：
//
//
// 输入：s = "0-1"
//
//
// 输出：0
//
// 解释：
//
//
//第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
//         ^
//第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
//         ^
//第 3 步："0-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
//          ^
//
//
//
// 示例 5：
//
//
// 输入：s = "words and 987"
//
//
// 输出：0
//
// 解释：
//
// 读取在第一个非数字字符“w”处停止。
//
//
//
// 提示：
//
//
// 0 <= s.length <= 200
// s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
//
//
// Related Topics 字符串 👍 1839 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L8_StringToIntegerAtoi{

  public static void main(String[] args) {
       Solution solution = new L8_StringToIntegerAtoi().new Solution();
//      System.out.println(solution.myAtoi1("1337c0d3"));
      System.out.println(solution.myAtoi("-2147483649"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      public  Integer MAX = Integer.MAX_VALUE;
      public  Integer MIN = Integer.MIN_VALUE;

    public int myAtoi(String s) {
        //是否负数，默认整数
        int negative = 1;
        int res = 0;
        int index = 0;
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        //s.strip()
        while (index<length&& charArray[index]==' '){
            index++;
        }
        //空字符串
        if (index==length){
            return 0;
        }
        //-+
        if (charArray[index] == '-' || charArray[index] == '+') {
            negative = charArray[index] == '-' ? -1 : negative;
            index++;
        }
        while (index < length) {
            char cur = charArray[index];
            if (cur > '9' || cur < '0') {
                break;
            }
            int num = cur-'0';
            //预先判断累加计算是否会溢出
            if (res > (MAX - num) / 10) {
                return negative == 1 ? MAX : MIN;
            }
            res = res * 10 + num;
            index++;
        }
        return res * negative;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int myAtoi1(String s) {
            long res = 0;
            s = s.strip();
            boolean negative = false;
            boolean zero = true;
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (Character.isDigit(cur) && cur == '0' && zero) {
                    continue;
                } else if (Character.isDigit(cur)) {
                    zero = false;
                    res = res * 10 + (cur - '0');
                    if (negative && -res <= Integer.MIN_VALUE){
                        return Integer.MIN_VALUE;
                    } else if (!negative && res >= Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                } else if (i==0 && (cur == '-' || cur == '+')) {
                    negative = cur == '-';
                } else {
                    break;
                }
            }
            return negative ? -(int) res : (int) res;
        }


        public Integer myAtoi(String s) {

            s= s.strip();
            boolean negative = false;
            int i = 0, len = s.length();
            int limit = -Integer.MAX_VALUE;

            if (len > 0) {
                char firstChar = s.charAt(0);
                if (firstChar < '0') { // Possible leading "+" or "-"
                    if (firstChar == '-') {
                        negative = true;
                        limit = Integer.MIN_VALUE;
                    } else if (firstChar != '+') {
                        return 0;
                    }

                    if (len == 1) { // Cannot have lone "+" or "-"
                        return 0;
                    }
                    i++;
                }
                int multmin = limit / 10;
                int result = 0;
                while (i < len) {
                    if (!Character.isDigit(s.charAt(i))){
                        return negative ? result : -result;
                    }else {
                        int digit = Character.digit(s.charAt(i++), 10);
                        if (digit < 0 || result < multmin) {
                            return -limit;
                        }
                        result *= 10;
                        if (result < limit + digit) {
                            return -limit;
                        }
                        result -= digit;
                    }
                }
                return negative ? result : -result;
            } else {
                return 0;
            }
        }
    }
}