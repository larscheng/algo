//给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。 
//
// 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的
//字符 c 的 距离 。 
//
// 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "loveleetcode", c = "e"
//输出：[3,2,1,0,1,0,0,1,2,2,1,0]
//解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
//距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
//距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
//对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
//距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
// 
//
// 示例 2： 
//
// 
//输入：s = "aaab", c = "b"
//输出：[3,2,1,0]
// 
//
// 
//提示：
//
// 
// 1 <= s.length <= 10⁴ 
// s[i] 和 c 均为小写英文字母 
// 题目数据保证 c 在 s 中至少出现一次 
// 
//
// Related Topics 数组 双指针 字符串 👍 347 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L821_ShortestDistanceToACharacter{
      
  public static void main(String[] args) {
       Solution solution = new L821_ShortestDistanceToACharacter().new Solution();
       solution.shortestToChar("loveleetcode",'e');
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==c){
                target.add(i);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==c){
                result[i] = 0;
                continue;
            }
            for (Integer index : target) {
                result[i] = result[i] == 0 ? Math.abs(i - index) : Math.min(Math.abs(i - index), result[i]);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 遍历字符串每一个元素，如果当前元素为目标字符，则距离为0
     * 从当前元素出发，分别向左、右寻找目标字符，最先找到目标字符，即为最小距离
     *
     * O(n logn)/O(1)
     */
    class Solution2 {
        public int[] shortestToChar(String s, char c) {
            int[] result = new int[s.length()];

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    result[i] = 0;
                    continue;
                }
                int l = i--;
                int r = i++;
                while (l >= 0 || r < s.length()) {
                    if (l >= 0) {
                        if (s.charAt(l) == c) {
                            result[i] = Math.abs(i - l);
                            break;
                        } else {
                            l--;
                        }
                    }
                    if (r < s.length()) {
                        if (s.charAt(r) == c) {
                            result[i] = Math.abs(i - r);
                            break;
                        } else {
                            r++;
                        }
                    }
                }
            }
            return result;
        }
    }
    /**
     * O(n^2)/O(1)
     */
    class Solution1 {
        public int[] shortestToChar(String s, char c) {
            int[] result = new int[s.length()];
            int x = 0;
            int y = 0;
            while (x < s.length()) {

                if (s.charAt(x) == c) {
                    result[x] = 0;
                    x++;
                    continue;
                }

                if (y > s.length() - 1) {
                    x++;
                    y = 0;
                    continue;
                }
                if (s.charAt(y) == c) {
                    result[x] = result[x] == 0 ? Math.abs(x - y) : Math.min(Math.abs(x - y), result[x]);
                }
                y++;
            }
            return result;
        }
    }
}