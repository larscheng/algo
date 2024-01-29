//如果长度为 n 的数组 nums 满足下述条件，则认为该数组是一个 漂亮数组 ： 
//
// 
// nums 是由范围 [1, n] 的整数组成的一个排列。 
// 对于每个 0 <= i < j < n ，均不存在下标 k（i < k < j）使得 2 * nums[k] == nums[i] + nums[j] 。
// 
// 
//
// 给你整数 n ，返回长度为 n 的任一 漂亮数组 。本题保证对于给定的 n 至少存在一个有效答案。 
//
// 
//
// 示例 1 ： 
//
// 
//输入：n = 4
//输出：[2,1,4,3]
// 
//
// 示例 2 ： 
//
// 
//输入：n = 5
//输出：[3,1,2,5,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
//
// 
//
// Related Topics 数组 数学 分治 👍 224 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class L932_BeautifulArray{
      
  public static void main(String[] args) {
       Solution solution = new L932_BeautifulArray().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<Integer, int[]> memo;
    public int[] beautifulArray(int N) {
        memo = new HashMap<>();
        memo.put(1, new int[]{1});
        return f(N);
    }

    private int[] f(int N){
        if(!memo.containsKey(N)){
            int index = 0;
            int[] res = new int[N];
            for(int x : f((N + 1) / 2)){
                res[index++] = 2 * x - 1;
            }
            for(int x : f(N / 2)){
                res[index++] = 2 * x;
            }
            memo.put(N, res);
        }
        return memo.get(N);
    }
}





//leetcode submit region end(Prohibit modification and deletion)


}