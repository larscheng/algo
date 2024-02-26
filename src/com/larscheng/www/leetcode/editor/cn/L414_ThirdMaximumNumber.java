//给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3, 2, 1]
//输出：1
//解释：第三大的数是 1 。 
//
// 示例 2： 
//
// 
//输入：[1, 2]
//输出：2
//解释：第三大的数不存在, 所以返回最大的数 2 。
// 
//
// 示例 3： 
//
// 
//输入：[2, 2, 3, 1]
//输出：1
//解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
//此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？ 
//
// Related Topics 数组 排序 👍 454 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.PriorityQueue;
import java.util.TreeSet;

public class L414_ThirdMaximumNumber{
      
  public static void main(String[] args) {
       Solution solution = new L414_ThirdMaximumNumber().new Solution();
      System.out.println(solution.thirdMax(new int[]{1,1,2}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int thirdMax(int[] nums) {
        //去重，升序
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
            if (treeSet.size() > 3) {
                //剔除最小的
                treeSet.remove(treeSet.first());
            }
        }
        return treeSet.size() == 3 ? treeSet.first() : treeSet.last();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int thirdMax(int[] nums) {

        //小顶堆，限制大小为3
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (queue.contains(num)){
                continue;
            }
            queue.offer(num);
            if (queue.size()>3){
                queue.poll();
            }
        }
        if (queue.size() == 1) {
            return queue.peek();
        }
        if (queue.size() == 2) {
            queue.poll();
        }
        return queue.peek();
    }
}

}