//给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。 
//
// 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。 
//
// 返回一个表示每个字符串片段的长度的列表。 
//
// 
//示例 1：
//
// 
//输入：s = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。 
//
// 示例 2： 
//
// 
//输入：s = "eccbbbbdec"
//输出：[10]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 贪心 哈希表 双指针 字符串 👍 1135 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L763_PartitionLabels{
      
  public static void main(String[] args) {
       Solution solution = new L763_PartitionLabels().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 贪心算法
     * 记录每个字符最后出现的位置
     * 遍历字符串，获取每个字符的最后出现位置end，此片段的结束位置即为end
     * 遍历的过程中不断更新这个end值，end = max(end,当前元素的最后出现位置)
     * 直到遍历到end处，start到end即为这一片段的长度，记录长度，继续遍历
     *
     * O(n)/o(1)
     */
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            //收集所有元素最后出现的位置
            last[s.charAt(i) - 'a'] = i;
        }
        int start = 0, end = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            //不断更新片段边界
            end = Math.max(end, last[s.charAt(i) - 'a']);
            //遍历到边界时，收集片段长度，重置开始位置
            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}