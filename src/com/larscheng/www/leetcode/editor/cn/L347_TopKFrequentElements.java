//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
//
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 1745 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;
import java.util.stream.Collectors;

public class L347_TopKFrequentElements{
      
  public static void main(String[] args) {
       Solution solution = new L347_TopKFrequentElements().new Solution();
//       solution.topKFrequent(new int[]{1,3,3,3,2,2,5,5,5,5,5,4,4,4,4},3);
//       solution.topKFrequent(new int[]{1,1,1,2,2,3},2);
        solution.topKFrequent(new int[]{4,1,-1,2,-1,2,3},2);
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> map.get(b)-map.get(a));
        queue.addAll(map.keySet());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

    class Solution3 {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer,Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            List<Map.Entry<Integer,Integer>> entries = new ArrayList<>(map.entrySet());
            //按照出现频率排序
            entries.sort((a, b) -> b.getValue() - a.getValue());
            //取map中前k个
            return entries.stream().mapToInt(Map.Entry::getKey).limit(k).toArray();
        }
    }
    class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer,Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            return map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .mapToInt(Integer::intValue)
                    .limit(k)
                    .toArray();
        }
    }

}