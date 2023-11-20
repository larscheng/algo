//给你一个由 X-Y 平面上的点组成的数组 points ，其中 points[i] = [xi, yi] 。从其中取任意三个不同的点组成三角形，返回能组成的
//最大三角形的面积。与真实值误差在 10⁻⁵ 内的答案将会视为正确答案。 
//
// 
//
// 示例 1： 
// 
// 
//输入：points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
//输出：2.00000
//解释：输入中的 5 个点如上图所示，红色的三角形面积最大。
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,0],[0,0],[0,1]]
//输出：0.50000
// 
//
// 
//
// 提示： 
//
// 
// 3 <= points.length <= 50 
// -50 <= xi, yi <= 50 
// 给出的所有点 互不相同 
// 
//
// Related Topics 几何 数组 数学 👍 192 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L812_LargestTriangleArea{

  public static void main(String[] args) {
       Solution solution = new L812_LargestTriangleArea().new Solution();
       //= [(y1 + y2) * (x1 - x2)]/2 + [(y3 + y1) * (x3 - x1)]/2 - [(y2 + y3) * (x3 - x2)]/2
       //= 1/2 * [x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2)]
       //= 1/2* (x1y2-x1y3+x2y3-x2y1+x3y1-x3y2)
       //= 1/2* (x1y2+x2y3+x3y1-x1y3-x2y1-x3y2)
       // 梯形面积差计算方式

  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double largestTriangleArea(int[][] points) {
        double s=0;
        for (int i = 0; i < points.length-2; i++) {
            for (int j = 0; j < points.length-1; j++) {
                for (int k = 0; k < points.length; k++) {
                    int x1 = points[i][0],y1 = points[i][1];
                    int x2 = points[j][0],y2 = points[j][1];
                    int x3 = points[k][0],y3 = points[k][1];
                    double temp = 0.5 *Math.abs(x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2);
                    if (s<temp){
                        s=temp;
                    }
                }
            }
        }
        return s;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}