//给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字
//符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。 
//
// 示例： 
//
// 输入：
//big = "mississippi"
//smalls = ["is","ppi","hi","sis","i","ssippi"]
//输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
// 
//
// 提示： 
//
// 
// 0 <= len(big) <= 1000 
// 0 <= len(smalls[i]) <= 1000 
// smalls的总字符数不会超过 100000。 
// 你可以认为smalls中没有重复字符串。 
// 所有出现的字符均为英文小写字母。 
// 
//
// Related Topics 字典树 数组 哈希表 字符串 字符串匹配 滑动窗口 👍 53 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L面试题17_17_MultiSearchLcci{
      
  public static void main(String[] args) {
       Solution solution = new L面试题17_17_MultiSearchLcci().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] multiSearch(String big, String[] smalls) {
        if (smalls == null || smalls.length == 0) {
            return new int[][]{};
        }
        int length = smalls.length;
        int[][] res = new int[length][];
        for (int i = 0; i < length; i++) {
            if ("".equals(smalls[i])) {
                res[i] = new int[]{};
                continue;
            }
            List<Integer> list = new ArrayList<>();
            int index = 0;
            while (big.indexOf(smalls[i], index) != -1) {
                int position = big.indexOf(smalls[i], index);
                list.add(position);
                //从当前匹配到的位置的下一个位置开始继续匹配
                index = position + 1;
            }
            res[i] = list.stream().mapToInt(Integer::intValue).toArray();
        }
        return res;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}