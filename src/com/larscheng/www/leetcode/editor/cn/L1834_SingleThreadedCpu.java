//给你一个二维数组 tasks ，用于表示 n 项从 0 到 n - 1 编号的任务。其中 tasks[i] = [enqueueTimei, 
//processingTimei] 意味着第 i 项任务将会于 enqueueTimei 时进入任务队列，需要 processingTimei 的时长完成执行。 
//
// 现有一个单线程 CPU ，同一时间只能执行 最多一项 任务，该 CPU 将会按照下述方式运行： 
//
// 
// 如果 CPU 空闲，且任务队列中没有需要执行的任务，则 CPU 保持空闲状态。 
// 如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的
//任务开始执行。 
// 一旦某项任务开始执行，CPU 在 执行完整个任务 前都不会停止。 
// CPU 可以在完成一项任务后，立即开始执行一项新任务。 
// 
//
// 返回 CPU 处理任务的顺序。 
//
// 
//
// 示例 1： 
//
// 输入：tasks = [[1,2],[2,4],[3,2],[4,1]]
//输出：[0,2,3,1]
//解释：事件按下述流程运行： 
//- time = 1 ，任务 0 进入任务队列，可执行任务项 = {0}
//- 同样在 time = 1 ，空闲状态的 CPU 开始执行任务 0 ，可执行任务项 = {}
//- time = 2 ，任务 1 进入任务队列，可执行任务项 = {1}
//- time = 3 ，任务 2 进入任务队列，可执行任务项 = {1, 2}
//- 同样在 time = 3 ，CPU 完成任务 0 并开始执行队列中用时最短的任务 2 ，可执行任务项 = {1}
//- time = 4 ，任务 3 进入任务队列，可执行任务项 = {1, 3}
//- time = 5 ，CPU 完成任务 2 并开始执行队列中用时最短的任务 3 ，可执行任务项 = {1}
//- time = 6 ，CPU 完成任务 3 并开始执行任务 1 ，可执行任务项 = {}
//- time = 10 ，CPU 完成任务 1 并进入空闲状态
// 
//
// 示例 2： 
//
// 输入：tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
//输出：[4,3,2,0,1]
//解释：事件按下述流程运行： 
//- time = 7 ，所有任务同时进入任务队列，可执行任务项  = {0,1,2,3,4}
//- 同样在 time = 7 ，空闲状态的 CPU 开始执行任务 4 ，可执行任务项 = {0,1,2,3}
//- time = 9 ，CPU 完成任务 4 并开始执行任务 3 ，可执行任务项 = {0,1,2}
//- time = 13 ，CPU 完成任务 3 并开始执行任务 2 ，可执行任务项 = {0,1}
//- time = 18 ，CPU 完成任务 2 并开始执行任务 0 ，可执行任务项 = {1}
//- time = 28 ，CPU 完成任务 0 并开始执行任务 1 ，可执行任务项 = {}
//- time = 40 ，CPU 完成任务 1 并进入空闲状态 
//
// 
//
// 提示： 
//
// 
// tasks.length == n 
// 1 <= n <= 10⁵ 
// 1 <= enqueueTimei, processingTimei <= 10⁹ 
// 
//
// Related Topics 数组 排序 堆（优先队列） 👍 101 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1834_SingleThreadedCpu{
      
  public static void main(String[] args) {
       Solution solution = new L1834_SingleThreadedCpu().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] newTasks  = new int[n][3];

        //转为3元数组，记录每个任务的下标
        for (int i = 0; i < n; i++) {
            newTasks[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }

        //任务按照入对时间排序
        Arrays.sort(newTasks, (a, b) -> a[0] - b[0]);
        //优先队列：选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                // 执行时间相同时，下标最小的先执行
                return a[2] - b[2];
            } else {
                // 默认按照耗时时间最短的先执行
                return a[1] - b[1];
            }
        });

        int[] res = new int[n];
        //当前执行时间
        int time = 0;
        //已执行的任务数量
        int resIdx = 0;
        //已进入队列的数量
        int enqIdx = 0;
        while (resIdx < n) {
            //小于等于 当前执行时间time的任务入队
            while (enqIdx < n && newTasks[enqIdx][0] <= time) {
                queue.offer(newTasks[enqIdx++]);
            }
            //优先队列为空，当前执行时间time=未入队的任务
            if (queue.isEmpty()) {
                time = newTasks[enqIdx][0];
                continue;
            }
            //弹出任务，开始执行，当前执行时间time += 当前任务的执行耗时
            int[] nextJob = queue.poll();
            res[resIdx++] = nextJob[2];
            time += nextJob[1];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}