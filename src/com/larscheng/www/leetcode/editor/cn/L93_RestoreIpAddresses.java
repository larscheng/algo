//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
// 
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新
//排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
//
// Related Topics 字符串 回溯 👍 1429 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L93_RestoreIpAddresses{
      
  public static void main(String[] args) {
       Solution solution = new L93_RestoreIpAddresses().new Solution();
      System.out.println(solution.restoreIpAddresses("101023"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //收集ip地址
      List<String> result = new ArrayList<>();
      //收集分段ip数字
      LinkedList<String> segment = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        dfs(s,0);
        return result;
    }

    private void dfs(String str, int start) {
        //结束条件
        if (start == str.length() && segment.size() == 4) {
            result.add(String.join(".", segment));
            return;
        }
        //剪枝1，已经收集够4分段，但是字符串还有剩余
        if (segment.size()==4&&start< str.length()){
            return;
        }
        //3中分割步长，1、12、123
        for (int i = 1; i <= 3; i++) {
            //剪枝2，分割右边界越界
            if (start + i > str.length()) {
                return;
            }
            //剪枝3，出现0x、0xx
            if (i>1&& str.charAt(start)=='0'){
                return;
            }
            String substring = str.substring(start, start + i);

            //剪枝4，3位数大于255
            if (i==3 && Integer.parseInt(substring)>255){
                return;
            }
            segment.add(substring);

            dfs(str, start + i);

            segment.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}