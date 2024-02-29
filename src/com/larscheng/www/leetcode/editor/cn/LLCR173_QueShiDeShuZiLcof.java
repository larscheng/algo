//某班级 n 位同学的学号为 0 ~ n-1。点名结果记录于升序数组 records。假定仅有一位同学缺席，请返回他的学号。 
//
// 
//
// 示例 1: 
//
// 
//输入: records = [0,1,2,3,5]
//输出: 4
// 
//
// 示例 2: 
//
// 
//输入: records = [0, 1, 2, 3, 4, 5, 6, 8]
//输出: 7 
//
// 
//
// 提示： 
//
// 1 <= records.length <= 10000 
//
// Related Topics 位运算 数组 哈希表 数学 二分查找 👍 425 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class LLCR173_QueShiDeShuZiLcof{
      
  public static void main(String[] args) {
       Solution solution = new LLCR173_QueShiDeShuZiLcof().new Solution();
      //System.out.println(solution.takeAttendance(new int[]{0,1,2,3,5}));
//      System.out.println(solution.takeAttendance(new int[]{0, 1, 2, 3, 4, 5, 6, 8}));
//      System.out.println(solution.takeAttendance(new int[]{0, 1,  3, 4, 5, 6,7, 8}));
      System.out.println(solution.takeAttendance(new int[]{0}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int takeAttendance(int[] records) {
        int left = 0, right = records.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (records[mid] == mid) {
                //有序部分数字等于下标，缺失数字在右区间
                left = mid + 1;
            } else {
                //缺失值的首个数组再左区间
                right = mid - 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}