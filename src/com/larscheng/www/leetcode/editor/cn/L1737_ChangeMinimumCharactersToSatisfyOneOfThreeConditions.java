//给你两个字符串 a 和 b ，二者均由小写字母组成。一步操作中，你可以将 a 或 b 中的 任一字符 改变为 任一小写字母 。
//
// 操作的最终目标是满足下列三个条件 之一 ： 
//
// 
// a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母 。 
// b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母 。 
// a 和 b 都 由 同一个 字母组成。 
// 
//
// 返回达成目标所需的 最少 操作数。 
//
// 
//
// 示例 1： 
//
// 输入：a = "aba", b = "caa"
//输出：2
//解释：满足每个条件的最佳方案分别是：
//1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b 中的每个字母；
//2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母；
//3) 将 a 变为 "aaa" 并将 b 变为 "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。
//最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。
// 
//
// 示例 2： 
//
// 输入：a = "dabadd", b = "cda"
//输出：3
//解释：满足条件 1 的最佳方案是将 b 变为 "eee" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= a.length, b.length <= 10⁵ 
// a 和 b 只由小写字母组成 
// 
//
// Related Topics 哈希表 字符串 计数 前缀和 👍 62 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1737_ChangeMinimumCharactersToSatisfyOneOfThreeConditions{
      
  public static void main(String[] args) {
       Solution solution = new L1737_ChangeMinimumCharactersToSatisfyOneOfThreeConditions().new Solution();
      System.out.println(solution.minCharacters("ae", "b"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCharacters(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        //收集每个字符出现的数量
        int[] countA = new int[26];
        int[] countB = new int[26];
        for (char c : a.toCharArray()) {
            countA[c-'a']++;
        }
        for (char c : b.toCharArray()) {
            countB[c-'a']++;
        }
        //计算每个字符的前缀和
        int[] preA = new int[26];
        int[] preB = new int[26];
        for (int i = 0; i < 26 ; i++) {
            preA[i] = (i == 0) ? countA[0] : preA[i - 1] + countA[i];
            preB[i] = (i == 0) ? countB[0] : preB[i - 1] + countB[i];
        }

        //遍历26个字母，检查三个条件需要的操作数量，取最小
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (i > 0) {
                //a<b
                res = Math.min(res, lenA - preA[i - 1] + preB[i - 1]);
                //a>b
                res = Math.min(res, preA[i - 1] + lenB - preB[i - 1]);
            }
            //a==b
            res = Math.min(res, lenA - countA[i] + lenB - countB[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}