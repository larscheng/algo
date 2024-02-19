//给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 请注意，它是 排序后 的第 k 小元素，而不是第 
//k 个 不同 的元素。 
//
// 你必须找到一个内存复杂度优于 O(n²) 的解决方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//输出：13
//解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[-5]], k = 1
//输出：-5
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length 
// n == matrix[i].length 
// 1 <= n <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列 
// 1 <= k <= n² 
// 
//
// 
//
// 进阶： 
//
// 
// 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题? 
// 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。 
// 
//
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 👍 1031 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.PriorityQueue;

public class L378_KthSmallestElementInASortedMatrix{

    public static void main(String[] args) {
        Solution solution = new L378_KthSmallestElementInASortedMatrix().new Solution();
        System.out.println(solution.kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        //System.out.println(solution.kthSmallest(new int[][]{ {-5}}, 1));
        System.out.println(solution.kthSmallest(new int[][]{ {1,2},{1,3}}, 2));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix[0].length - 1];

        while (left < right){
            int mid = left + (right - left) / 2;
            //小于mid值的数量
            int count = getCount(matrix, mid);

            if (count < k) {
                //目标数在右侧
                left = mid +1;
            }else {
                //目标数在左侧
                right = mid;
            }
        }
        return right;
    }

    private int getCount(int[][] matrix, int mid) {
        int count = 0;
        int x = 0, y = 0;
        while (x < matrix.length && y < matrix[0].length) {
            if (matrix[x][y] <= mid) {
                count++;
            }
            y++;
            if (y > matrix[0].length - 1) {
                y = 0;
                x++;
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * 大顶堆，O(n^2)
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                queue.offer(matrix[i][j]);
                if (queue.size()>k){
                    //弹出堆顶当前最大的元素
                    queue.poll();
                }
            }
        }
        return queue.peek();
    }
}

}