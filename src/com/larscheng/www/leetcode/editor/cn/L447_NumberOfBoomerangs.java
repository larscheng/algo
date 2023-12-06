//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中
// i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。 
//
// 返回平面上所有回旋镖的数量。 
//
// 示例 1： 
//
// 
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：points = [[1,1]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == points.length 
// 1 <= n <= 500 
// points[i].length == 2 
// -10⁴ <= xi, yi <= 10⁴ 
// 所有点都 互不相同 
// 
//
// Related Topics 数组 哈希表 数学 👍 255 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

public class L447_NumberOfBoomerangs{
      
  public static void main(String[] args) {
       Solution solution = new L447_NumberOfBoomerangs().new Solution();
       
  }

    /**
     * 以i为起点，统计距离i相等的j,k的组合个数，
     * 假设有x个点到i的距离相等，从x中取出2个则有 x(x-1) 种取法
     * 遍历所有的点，当前坐标点作为起点
     * 计算他与其他坐标点的距离，记录相同距离出现的次数到hashmap
     * 遍历map，累加符合条件的坐标点数量
     *
     * 两点间距离：(x1-x2)^2 + (y1-y2)^2 = distance^2
     * O(n2) / O(n)
     *
     */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        for (int[] a : points) {
            //distance -> count
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] b : points) {
                if (a == b) {
                    continue;
                }
                int distance = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                result += entry.getValue() * (entry.getValue() - 1);
            }
        }

        return result;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}