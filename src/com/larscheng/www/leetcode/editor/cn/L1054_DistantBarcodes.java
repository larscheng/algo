//在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。 
//
// 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：barcodes = [1,1,1,2,2,2]
//输出：[2,1,2,1,2,1]
// 
//
// 示例 2： 
//
// 
//输入：barcodes = [1,1,1,1,2,2,3,3]
//输出：[1,3,1,3,2,1,2,1] 
//
// 
//
// 提示： 
//
// 
// 1 <= barcodes.length <= 10000 
// 1 <= barcodes[i] <= 10000 
// 
//
// Related Topics 贪心 数组 哈希表 计数 排序 堆（优先队列） 👍 187 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L1054_DistantBarcodes{
      
  public static void main(String[] args) {
       Solution solution = new L1054_DistantBarcodes().new Solution();
      System.out.println(Arrays.toString(solution.rearrangeBarcodes(new int[]{1, 1, 1, 2, 2, 2})));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0) + 1);
        }
        //元素出现次数的大顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->b[0]-a[0]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getValue(), entry.getKey()});
        }
        int[] res = new int[barcodes.length];

        for (int i = 0; i < barcodes.length; i++) {
            int[] item = queue.poll();
            int count = item[0];
            int num = item[1];

            if (i == 0 || res[i - 1] != num) {
                res[i] = num;
                if (count > 1) {
                    queue.offer(new int[]{count - 1, num});
                }
            }else {
                int[] item2 = queue.poll();
                int count2 = item2[0];
                int num2 = item2[1];

                res[i] = num2;
                if (count2 > 1) {
                    queue.offer(new int[]{count2 - 1, num2});
                }

                queue.offer(new int[]{count, num});
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}