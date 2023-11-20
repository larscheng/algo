//给你一个正整数 n ，请你返回 n 的 惩罚数 。 
//
// n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和： 
//
// 
// 1 <= i <= n 
// i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：182
//解释：总共有 3 个整数 i 满足要求：
//- 1 ，因为 1 * 1 = 1
//- 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
//- 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
//因此，10 的惩罚数为 1 + 81 + 100 = 182
// 
//
// 示例 2： 
//
// 
//输入：n = 37
//输出：1478
//解释：总共有 4 个整数 i 满足要求：
//- 1 ，因为 1 * 1 = 1
//- 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
//- 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
//- 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
//因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
//
// Related Topics 数学 回溯 👍 70 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L2698_FindThePunishmentNumberOfAnInteger{
      
  public static void main(String[] args) {
       Solution solution = new L2698_FindThePunishmentNumberOfAnInteger().new Solution();
      System.out.println(solution.punishmentNumber(10));
       Solution1 solution1 = new L2698_FindThePunishmentNumberOfAnInteger().new Solution1();
//      System.out.println(solution1.punishmentNumber(10));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int punishmentNumber(int n) {
        int result= 1;
        for (int i = 2; i <= n; i++) {
            int ii = i * i;
            if (check(i, String.valueOf(ii),0,0)) {
                result += ii;
            }
        }

        return result;
    }

    private boolean check(int i, String temp, int index, int sum) {
        //index==length
        if (index == temp.length() && i == sum) {
            //36(i) ，因为 36 * 36 = 1296(temp) ，且 1296 可以分割成 1 + 29 + 6(sum) 。
            return true;
        }

        int current = 0;
        for (int j = index; j < temp.length(); j++) {
            current = current * 10 + Integer.parseInt(String.valueOf(temp.charAt(j)));

            if (current + sum > i) {
                //不需要继续了
                return false;
            }

            if (check(i, temp, j + 1, sum + current)) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (check(i * i, i)) {
                ans += i * i;
            };
        }
        return ans;
    }
    boolean check(int t, int x) {
        //当子串值==x，说明符合条件
        if (t == x) return true;
        int d = 10;
        //10进制数，余数小于等于i
        while (t >= d && t % d <= x) {
            // 去一位，减掉余数
            if (check(t / d, x - (t % d))) return true;
            d *= 10;
        }
        return false;
    }
}


}