//给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母
//。如果不存在这样的两个单词，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//输出：16 
//解释：这两个单词为 "abcw", "xtfn"。 
//
// 示例 2： 
//
// 
//输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
//输出：4 
//解释：这两个单词为 "ab", "cd"。 
//
// 示例 3： 
//
// 
//输入：words = ["a",".gitignore","aaa","aaaa"]
//输出：0 
//解释：不存在这样的两个单词。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= words.length <= 1000 
// 1 <= words[i].length <= 1000 
// words[i] 仅包含小写字母 
// 
//
// Related Topics 位运算 数组 字符串 👍 468 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;

public class L318_MaximumProductOfWordLengths{
      
  public static void main(String[] args) {
       Solution solution = new L318_MaximumProductOfWordLengths().new Solution();
      System.out.println(solution.maxProduct(new String[]{"abcw", "baz", "foo", "bar","baar", "xtfn", "abcdef"}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //复杂度O(n^2)
    public int maxProduct(String[] words) {
        int result = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            int size = words[i].length();
            //二进制存储每个字符
            int mask = 0;
            for (int j = 0; j < words[i].length(); j++) {
                int c = words[i].charAt(j) - 'a';
                // 按位或 同0为0，不同为1
                mask = mask | (1 << c);
            }
            //mask相同时，只保留字符长度最大的
            if (!map.containsKey(mask) || map.get(mask) < size) {
                map.put(mask, size);
            }
        }

        for (Integer maskX:map.keySet()) {
            for (Integer maskY : map.keySet()) {
                //按位与 同1为1，其他为0
                if ((maskX&maskY)==0){
                    result = Math.max(result, map.get(maskX) * map.get(maskY));
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        //复杂度O(n^2)
        public int maxProduct(String[] words) {
            int result = 0;
            int[] mask = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[i].length(); j++) {
                    int c = words[i].charAt(j) - 'a';
                    // 按位或 同0为0，不同为1
                    mask[i] = mask[i] | (1 << c);
                }
            }
            for (int i = 0; i < words.length; i++) {
                for (int j = i+1; j < words.length; j++) {
                    //按位与 同1为1，其他为0
                    if ((mask[i]&mask[j])==0){
                        result = Math.max(result, words[i].length() * words[j].length());
                    }
                }
            }
            return result;
        }
    }
    class Solution1 {
        //复杂度大于O(n^2)
        public int maxProduct(String[] words) {
            int result = 0;
            for (int i = 0; i < words.length-1; i++) {
                for (int j = i+1; j < words.length ; j++) {
                    String a = words[i];
                    String b = words[j];
                    boolean repeat = false;
                    for (int k = 0; k < a.length(); k++) {
                        if (b.contains(String.valueOf(a.charAt(k)))){
                            repeat = true;
                            break;
                        }
                    }
                    if (!repeat){
                        result = Math.max(result, a.length() * b.length());
                    }
                }
            }
            return result;
        }
    }

}