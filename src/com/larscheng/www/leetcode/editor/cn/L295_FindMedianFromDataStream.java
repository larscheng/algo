//中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。 
//
// 
// 例如 arr = [2,3,4] 的中位数是 3 。 
// 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。 
// 
//
// 实现 MedianFinder 类: 
//
// 
// MedianFinder() 初始化 MedianFinder 对象。 
// void addNum(int num) 将数据流中的整数 num 添加到数据结构中。 
// double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10⁻⁵ 以内的答案将被接受。 
// 
//
// 示例 1： 
//
// 
//输入
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//输出
//[null, null, null, 1.5, null, 2.0]
//
//解释
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0 
//
// 提示: 
//
// 
// -10⁵ <= num <= 10⁵ 
// 在调用 findMedian 之前，数据结构中至少有一个元素 
// 最多 5 * 10⁴ 次调用 addNum 和 findMedian 
// 
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 999 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class L295_FindMedianFromDataStream{
      
  public static void main(String[] args) {
      MedianFinder solution = new L295_FindMedianFromDataStream().new MedianFinder();
      solution.addNum(1);
      solution.addNum(2);
      System.out.println(solution.findMedian());
      solution.addNum(3);
      System.out.println(solution.findMedian());
  }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     *
     * 两个堆（优先队列）把添加的数，分为较大部分存放在小顶堆max，和较小部分存放在大顶堆min
     * 最终查询中数时，两堆大小不相同，则为奇数，取小顶堆堆顶元素，否则两堆堆顶之和除2
     * add时就需要不断维护，大的数放max，小的数放min
     * add:O(logn)：堆的弹出和插入
     * find:O(1)
     */
    class MedianFinder {
    PriorityQueue<Integer> max;
    PriorityQueue<Integer> min;
    public MedianFinder() {
        //小顶堆，保存较大的数，小的先出堆
        max = new PriorityQueue<>();
        //大顶堆，保存较小的数，大的先出堆
        min = new PriorityQueue<>((o1,o2)->o2-o1);
    }
    
    public void addNum(int num) {
        //每次add，都会操作两个堆，保证max放较大的，min放较小的
        //第一次add时，数字经过操作最后放在max，所以奇数find时，max堆顶就是中位数
        if (max.size() != min.size()) {
            max.add(num);
            min.add(max.poll());
        } else {
            min.add(num);
            max.add(min.poll());
        }
    }

    public double findMedian() {
        return max.size() != min.size() ? max.peek() : (max.peek() + min.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)


}