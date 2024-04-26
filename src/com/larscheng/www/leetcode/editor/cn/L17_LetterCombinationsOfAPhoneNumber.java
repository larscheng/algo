//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
//
// Related Topics 哈希表 字符串 回溯 👍 2805 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L17_LetterCombinationsOfAPhoneNumber{
      
  public static void main(String[] args) {
       Solution solution = new L17_LetterCombinationsOfAPhoneNumber().new Solution();
      System.out.println(solution.letterCombinations("23"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //回溯
    //哈希表记录每个数字与字符的对应关系
    //从字符串首位字符为起点，开始递归收集字母组合，回溯重置状态
    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if (digits.isEmpty()){
            return res;
        }
        Map<Character,char[]> map = new HashMap<>(){
            {
                put('2',new char[]{'a','b','c'});
                put('3',new char[]{'d','e','f'});
                put('4',new char[]{'g','h','i'});
                put('5',new char[]{'j','k','l'});
                put('6',new char[]{'m','n','o'});
                put('7',new char[]{'p','q','r','s'});
                put('8',new char[]{'t','u','v'});
                put('9',new char[]{'w','x','y','z'});
            }
        };
        backtrack(map,digits,res,0,new StringBuffer());
        return res;
    }

    private void backtrack(Map<Character, char[]> map, String digits, List<String> res, int index, StringBuffer temp) {
        if (index == digits.length()) {
            res.add(temp.toString());
        } else {
            for (char letter : map.get(digits.charAt(index))) {
                temp.append(letter);
                backtrack(map, digits, res, index + 1, temp);
                temp.deleteCharAt(index);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}