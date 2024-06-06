//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 2408 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;
import java.util.stream.Collectors;

public class L215_KthLargestElementInAnArray{
      
  public static void main(String[] args) {
       Solution solution = new L215_KthLargestElementInAnArray().new Solution();
      System.out.println(solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 快速排序
     * https://blog.csdn.net/qq_61422622/article/details/131667258
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int findKthLargest(int[] nums, int k) {
        List<Integer> num = Arrays.stream(nums).boxed().collect(Collectors.toList());

        return quickSort(num,k);
    }


    private int quickSort(List<Integer> num, int k) {
        //随机一个下标位置作为标记位
        //int flag = num.get(new Random().nextInt(num.size()));
        Random rand = new Random();
        int flag = num.get(rand.nextInt(num.size()));

        List<Integer> small = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();
        List<Integer> equls = new ArrayList<>();

        for (Integer integer : num) {
            if (integer>flag) {
                bigger.add(integer);
            }else if (integer<flag) {
                small.add(integer);
            }else{
                equls.add(integer);
            }
        }

        if (k<=bigger.size()) {
            //topk在bigger中，递归排序
            return quickSort(bigger, k);
        }else if (bigger.size()+equls.size()<k) {
            //topk在small中 ,注意此时k有变化
            return quickSort(small, k-bigger.size()-equls.size());
        }
        //topk就是flag
        return flag;
    }

}

//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * 优先队列，小顶堆，堆顶是最小值
     * 遍历数组所有元素，都放进小顶堆，超出k个就弹出堆顶最小值
     * 最终剩下的就是较大的前k个，堆顶就是第k大
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(n)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size()>k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}

}