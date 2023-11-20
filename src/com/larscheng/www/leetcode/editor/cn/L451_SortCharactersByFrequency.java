//给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。 
//
// 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "tree"
//输出: "eert"
//解释: 'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
// 
//
// 示例 2: 
//
// 
//输入: s = "cccaaa"
//输出: "cccaaa"
//解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
// 
//
// 示例 3: 
//
// 
//输入: s = "Aabb"
//输出: "bbAa"
//解释: 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 5 * 10⁵ 
// s 由大小写英文字母和数字组成 
// 
//
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 👍 501 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class L451_SortCharactersByFrequency{
      
  public static void main(String[] args) {
       Solution solution = new L451_SortCharactersByFrequency().new Solution();
      System.out.println(solution.frequencySort("Aabb"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String frequencySort(String s) {

        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        map.forEach((key, value) -> priorityQueue.add(key));
        StringBuilder string = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Character poll = priorityQueue.poll();
            Integer count = map.get(poll);
            string.append(String.valueOf(poll).repeat(Math.max(0, count)));
        }
        return string.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    class Solution1 {
        public String frequencySort(String s) {

            HashMap<String,Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                String str = String.valueOf(s.charAt(i));
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
            List<String> list = map.keySet().stream().sorted(Comparator.comparing(map::get,Comparator.reverseOrder())).collect(Collectors.toList());

            StringBuilder stringBuffer = new StringBuilder();
            for (String s1 : list) {
                stringBuffer.append(String.valueOf(s1).repeat(Math.max(0, map.get(s1))));
            }
            return stringBuffer.toString();
        }
    }
}