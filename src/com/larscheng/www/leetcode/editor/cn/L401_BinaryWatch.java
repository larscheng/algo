//二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
// 
//
// 
// 例如，下面的二进制手表读取 "4:51" 。 
// 
//
// 
//
// 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。 
//
// 小时不会以零开头： 
//
// 
// 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。 
// 
//
// 分钟必须由两位数组成，可能会以零开头： 
//
// 
// 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：turnedOn = 1
//输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
// 
//
// 示例 2： 
//
// 
//输入：turnedOn = 9
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= turnedOn <= 10 
// 
//
// Related Topics 位运算 回溯 👍 425 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L401_BinaryWatch{
      
  public static void main(String[] args) {
       Solution solution = new L401_BinaryWatch().new Solution();
       solution.readBinaryWatch(2);
  }

    /**
     * 0000（00）- 1111（16）
     * 000000（00）- 111111（64）
     */
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<Integer> mins;
        List<String> res;

        public List<String> readBinaryWatch(int turnedOn) {
            res = new ArrayList<>();
            mins = new ArrayList<>();

            for (int i = 0; i < 6 && turnedOn >= i; i++) {
                //分针中点亮i个灯
                dfs(0, 0, i, true);
                //时针中点亮turnedOn-i个灯
                dfs(0, 0, turnedOn - i, false);
                //一轮结束，重置变量
                mins.clear();
            }

            return res;
        }

        private void dfs(int st, int sum, int cnt, boolean flag) {
            if (cnt == 0) {
                if (flag) {//收集分钟数
                    mins.add(sum);
                } else {//格式化：小时数+分钟数
                    format(sum);
                }
                return;
            }

            for (int i = st; i < (flag ? 6 : 4); i++) {
                int temp = (int) Math.pow(2, i);
                if (flag && sum + temp >= 60 || !flag && sum + temp >= 12) {
                    break;
                }
                dfs(i + 1, sum + temp, cnt - 1, flag);
            }
        }

        private void format(int sum) {
            if (!mins.isEmpty()) {
                for (int m : mins) {
                    // res.add(String.format("%d:%02d", sum, m));
                    StringBuilder sb = new StringBuilder();
                    sb.append(sum).append(':');
                    if (m < 10) {
                        sb.append('0');
                    }
                    sb.append(m);
                    res.add(sb.toString());
                }
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    res.add(i + ":" + (j < 10 ? "0" : "") + j);
                }
            }
        }
        return res;
    }
}

}