//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。 
//
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。 
//
// 
//
// 示例 1: 
//
// 
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2: 
//
// 
//输入: strs = [""]
//输出: [[""]]
// 
//
// 示例 3: 
//
// 
//输入: strs = ["a"]
//输出: [["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
//
// Related Topics 数组 哈希表 字符串 排序 👍 1826 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;
import java.util.stream.Collectors;

public class L49_GroupAnagrams{
      
  public static void main(String[] args) {
       Solution solution = new L49_GroupAnagrams().new Solution();
//      System.out.println(solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
      System.out.println(solution.groupAnagrams(new String[]{"ac","c"}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);

            List<String> list = map.getOrDefault(new String(charArray), new ArrayList<>());
            list.add(str);
            map.put(new String(charArray),list);
        }
        return new ArrayList<>(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.toCharArray().length; i++) {
                count[str.charAt(i) - 'a']++;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (count[i]>0){
                    //1a2b
                    sb.append(count[i]).append((char)( i + 'a'));
                }
            }
            List<String> list = map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(str);
            map.put(sb.toString(),list);
        }
        return new ArrayList<>(map.values());
    }
}

}