//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
//
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 930 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.Random;

public class L912_SortAnArray{
      
  public static void main(String[] args) {
       Solution solution = new L912_SortAnArray().new Solution();
      System.out.println(Arrays.toString(solution.sortArray(new int[]{5,1,1,2,0,0})));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSortThreeSpilt(int[] nums, int left, int right) {
        if (left>=right){
            return;
        }

        int index  = partition(nums,left,right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    /**
     * 三路快排
     * @param nums
     * @param left
     * @param right
     */
    private void quickSort(int[] nums, int left, int right) {
        //递归退出条件
        if (left >= right) {
            return;
        }
        //随机选取法
        int RandomIndex = left + new Random().nextInt(right - left + 1);
        swap(nums, left, RandomIndex);

        int pivot = nums[left];
        //重新定义边界，left/right不可变更
        int less = left;
        int more = right + 1;
        // 循环不变量：这里是左闭右闭区间
        // 小于nums[pivot]区间：[left + 1, less]
        // 等于nums[pivot]区间：[less + 1, i]
        // 大于nums[pivot]区间：[more, right]
        int cur = left + 1;
        while (cur < more) {
            if (nums[cur] < pivot) {
                less++;
                swap(nums, cur, less);
                cur++;
            } else if (nums[cur] == pivot) {
                cur++;
            } else {
                //这里不i++很重要！因为我们无法确定从尾部换来的元素是否小于nums[pivot]
                more--;
                swap(nums, cur, more);
            }
        }
        //less最后指向的一定是小于nums[pivot]的元素
        swap(nums, left, less);
        //同理more指向大于nums[pivot]的元素
        quickSort(nums, left, less - 1);
        quickSort(nums, more, right);
    }

    /**
     * 快慢指针
     * 随机取key，可以解决有序数组的排序复杂度恶化问题，无法解决大量重复的数组排序
     */
    private int partition(int[] nums, int left, int right) {
        //随机定key，将其交换到首位begin
        Random random = new Random();
        int key = random.nextInt(right - left + 1) + left;
        swap(nums, left, key);
        //随机定key，将其交换到首位end

        key = left;
        int prev = left;
        int cur = left+1;
        // cur遇到比key小的此时，prev++，即prev出发一步，之后交换prev与cur的位置的值，cur继续前进。
        while (cur<=right){
            //pre左边永远放的是小于key的值，所以每找到一个小于key的值，就向右一步并进行交换
            if (nums[cur] < nums[key] && ++prev != cur) {
                swap(nums, prev, cur);
            }
            cur++;
        }

        swap(nums, prev, key);
        key = prev;
        return key;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 挖坑法
     */
    private int partition1(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left<right){
            //从右往左找小于temp的数
            while (left< right && nums[right] > temp){
                //大于temp，移动指针继续找
                right--;
            }
            nums[left] = nums[right];
            //从左往右找大于temp的数，移动到右侧
            while (left< right && nums[left] < temp){
                //小于temp，移动指针继续找
                left++;
            }
            nums[right]=nums[left];
        }
        nums[left] = temp;
        //left==right
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution3 {
    public int[] sortArray(int[] nums) {

        int min = Arrays.stream(nums).min().getAsInt();
        //最小值为负数，所有数都减去负数min，保证最小值为0，出口处再统一加上min
        if (min<0){
            for (int i = 0; i < nums.length; i++) {
                nums[i] -=min;
            }
        }
        int length = String.valueOf(Arrays.stream(nums).max().getAsInt()).length();

        //[
        // 0,3,4,5
        // 1,11,21,31
        // 2,12,22,32
        // ....
        // ]
        int[][] bucket = new int[10][nums.length];
        int base = 1;


        for (int i = 0; i < length ; i++) {
            int[] temp = new int[10];
            for (int j = 0; j < nums.length; j++) {
                int num = Math.abs((nums[j]/base) % 10);
                bucket[num][temp[num]] = nums[j];
                temp[num] = temp[num] + 1;
            }

            //捞出，排下一位数
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                for (int k = 0; k < temp[j]; k++) {
                    nums[index] = bucket[j][k];
                    index++;
                }
            }
            base*=10;
        }

        if (min<0){
            for (int i = 0; i < nums.length; i++) {
                nums[i]+=min;
            }
        }

        return nums;
    }
}
    class Solution2 {
        public int[] sortArray(int[] nums) {

            spiltAndMerge(nums, 0, nums.length - 1);

            return nums;
        }

        public void spiltAndMerge(int[] nums,int start,int end){
            if (start < end) {
                //(start+end)/2 防止int溢出
                int mid = start + (end - start) / 2;
                //拆分左
                spiltAndMerge(nums, start, mid);
                //拆分右
                spiltAndMerge(nums, mid + 1, end);
                //合并
                merge(nums, start, mid, end);
            }
        }
        public void merge(int[] nums,int start,int mid,int end){
            int xStart = start,xEnd = mid;
            int yStart = mid+1,yEnd = end;
            int[] temp = new int[end - start + 1];
            int index = 0 ;
            //      [xStart,xEnd]        \           [yStart,yEnd]
            //[xStart,xEnd][yStart,yEnd] \ [xStart,xEnd][yStart,yEnd]
            while (xStart <= xEnd && yStart <= yEnd) {
                if (nums[xStart] < nums[yStart]) {
                    temp[index] = nums[xStart];
                    xStart++;
                } else {
                    temp[index] = nums[yStart];
                    yStart++;
                }
                index++;
            }
            //多出来的直接补在最后
            while (xStart<=xEnd){
                temp[index] = nums[xStart];
                xStart++;
                index++;
            }
            while (yStart<=yEnd){
                temp[index] = nums[yStart];
                yStart++;
                index++;
            }
            for (int i = 0; i < temp.length; i++) {
                nums[start+i] = temp[i];
            }
        }
    }
    class Solution1 {
        public int[] sortArray(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                boolean flag = false;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] > nums[j]) {
                        flag = true;
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
                if (!flag){
                    break;
                }
            }
            return nums;
        }
    }

}