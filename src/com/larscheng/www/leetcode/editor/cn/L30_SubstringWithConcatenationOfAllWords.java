//给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。 
//
// s 中的 串联子串 是指一个包含 words 中所有字符串以任意顺序排列连接起来的子串。 
//
// 
// 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，
//"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。 
// 
//
// 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
//子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
//子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
//输出顺序无关紧要。返回 [9,0] 也是可以的。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
//解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
//s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
//所以我们返回一个空数组。
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
//解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
//子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
//子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
//子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 和 s 由小写英文字母组成 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1052 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L30_SubstringWithConcatenationOfAllWords{
      
  public static void main(String[] args) {
       Solution solution = new L30_SubstringWithConcatenationOfAllWords().new Solution();

//       solution.findSubstring("barfoothefoobarman",new String[]{"foo","bar"});
       solution.findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"});
//       solution.findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","good"});
  }

    /**
     * 传统滑动窗口，指针每次移动步长为1，本题中每个单词的长度都相同，可以改造为每次移动步长1个单词
     * 改造后，从下标0开始，每次步长1个单词进行滑动窗口逻辑，会漏掉从下标1、2、3...开始的滑动窗口逻辑
     * 所以需要一个多起点、指定移动步长的滑动窗口
     * O(m + w*n) 收集每个单词出现频率 O(m)  多个起点(单词长度w)* 字符串长度 O(w*n)
     * O(m*w) 每个滑动窗口都需要一个map
     */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int strLen = s.length();
        int len = words.length;
        int wordLen = words[0].length();
        int subLen = words[0].length() * len;
        if (strLen<subLen){
            return result;
        }
        //map 记录words每个单词出现次数
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            //多个起点的滑动窗口，每次滑动一个单词长度wordLen
            Map<String,Integer> subMap = new HashMap<>();
            //起点为i，步长为wordLen
            for (int j = i; j <= strLen-wordLen; j+=wordLen) {
                String curWord = s.substring(j, j + wordLen);
                subMap.put(curWord, subMap.getOrDefault(curWord, 0) + 1);
                //判断窗口内是否需要移动
                if (j - i >= subLen) {
                    //移动左窗口：map中最左侧单词,出现次数减1或者删除
                    int start = j-subLen;
                    String first = s.substring(start, start + wordLen);
                    if (subMap.get(first)==1){
                        subMap.remove(first);
                    }else {
                        subMap.put(first,subMap.get(first)-1);
                    }
                }
                if (!map.containsKey(curWord)){
                    continue;
                }
                if (subMap.equals(map)){
                    result.add(j+wordLen-subLen);
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int strLen = s.length();
        int len = words.length;
        int wordLen = words[0].length();
        int subLen = words[0].length() * len;
        if (strLen<subLen){
            return result;
        }
        //map 记录words每个单词出现次数
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //遍历s每个元素为起点，截取子串长度sub
        for (int i = 0; i <= strLen - subLen; i++) {
            String subStr = s.substring(i, i + subLen);
            if (check(subStr, wordLen, map)) {
                result.add(i);
            }
        }
        return result;

    }

    private boolean check(String subStr, int wordLen, Map<String, Integer> map) {
        Map<String,Integer> subMap = new HashMap<>();
        for (int j = 0; j < subStr.length(); j += wordLen) {
            String word = subStr.substring(j, j + wordLen);
            if (!map.containsKey(word)){
                return false;
            }
            subMap.put(word, subMap.getOrDefault(word, 0) + 1);
        }
        return map.equals(subMap);
    }
}

}