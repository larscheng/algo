//给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。 
//
// 
// 删除子字符串 "ab" 并得到 x 分。 
// 
//
// 
// 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。 
// 
// 
// 删除子字符串"ba" 并得到 y 分。
// 
// 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。 
// 
// 
//
//
// 请返回对 s 字符串执行上面操作若干次能得到的最大得分。 
//
// 
//
// 示例 1： 
//
// 输入：s = "cdbcbbaaabab", x = 4, y = 5
//输出：19
//解释：
//- 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
//- 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
//- 删除 "cdbcbbaa" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
//- 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
//总得分为 5 + 4 + 5 + 5 = 19 。 
//
// 示例 2： 
//
// 输入：s = "aabbaaxybbaabb", x = 5, y = 4
//输出：20
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁵ 
// 1 <= x, y <= 10⁴ 
// s 只包含小写英文字母。 
// 
//
// Related Topics 栈 贪心 字符串 👍 35 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1717_MaximumScoreFromRemovingSubstrings{
      
  public static void main(String[] args) {
       Solution solution = new L1717_MaximumScoreFromRemovingSubstrings().new Solution();
       Solution1 solution1 = new L1717_MaximumScoreFromRemovingSubstrings().new Solution1();
      //System.out.println(solution.maximumGain("cdbcbbaaabab", 4, 5));
      //System.out.println(solution1.maximumGain("aabbaaxybbaabb", 5, 4));
      System.out.println(solution.maximumGain("aabbaaxybbaabb", 5, 4));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //O(n)/O(1)
    public int maximumGain(String s, int x, int y) {
        int res = 0;
        int aCount=0,bCount=0,left=0,count=0;
        while (left < s.length()) {
            char c = s.charAt(left++);
            if (c == 'a') {
                aCount++;
            }
            if (c == 'b') {
                bCount++;
            }
            //x,y的大小固定，所以永远只会命中一种case，count代表分数高的个数
            if ((c == 'a' && bCount > 0 && y > x) || (c == 'b' && aCount > 0 && x >= y)) {
                count++;
                aCount--;
                bCount--;
            }
            //此时剩余的一定是顺序相反的a/b或者b/a，取(aCount, bCount)最小值作为个数
            if ((c != 'a' && c != 'b') || left == s.length()) {
                if (x >= y) {
                    res += count * x + Math.min(aCount, bCount) * y;
                } else {
                    res += count * y + Math.min(aCount, bCount) * x;
                }
                aCount = bCount = count = 0;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    //O(n)/O(n)
    class Solution2 {
        int res = 0;
        public int maximumGain(String s, int x, int y) {
            if (x >= y) {
                s = remove1(s, x);
                s = remove2(s, y);
            } else {
                s = remove2(s, y);
                s = remove1(s, x);
            }
            return res;
        }

        private String remove1(String s, int x) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (index > 0 && sb.charAt(index - 1) == 'a' && c == 'b') {
                    res += x;
                    sb.deleteCharAt(index - 1);
                    index--;
                } else {
                    sb.append(c);
                    index++;
                }
            }
            return sb.toString();
        }
        private String remove2(String s, int y) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (index > 0 && sb.charAt(index - 1) == 'b' && c == 'a') {
                    res += y;
                    sb.deleteCharAt(index - 1);
                    index--;
                } else {
                    sb.append(c);
                    index++;
                }
            }
            return sb.toString();
        }
    }
    //暴力解法，超时
    class Solution1 {
        public int maximumGain(String s, int x, int y) {
            String r1 = "ab";
            String r2 = "ba";
            int score1 = x;
            int score2 = y;
            if (y>x){
                r1 = "ba";
                r2 = "ab";
                score1 = y;
                score2 = x;
            }

            int res = 0;
            while (true){
                String replace = s.replace(r1, "");
                if (replace.equals(s)){
                    String replace2 = replace.replace(r2,"");
                    if (replace2.equals(s)){
                        break;
                    }else {
                        res += score2 * ((s.length() - replace2.length()) / 2);
                        s = replace2;
                    }
                }else {
                    res += score1 * ((s.length() - replace.length()) / 2);
                    s = replace;
                }
            }
            return res;
        }
    }
}