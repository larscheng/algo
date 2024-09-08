//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 
//输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 和 num2 只能由数字组成。 
// num1 和 num2 都不包含任何前导零，除了数字0本身。 
// 
//
// Related Topics 数学 字符串 模拟 👍 1368 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L43_MultiplyStrings{
      
  public static void main(String[] args) {
       Solution solution = new L43_MultiplyStrings().new Solution();
      System.out.println(solution.multiply("9", "99"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     *     1 2 3
     *         5
     * -----------
     *       1 5
     *     1 0
     *     5
     *     6 1 5
     * -----------
     *   0 1 2 3    下标索引
     * i和j的计算结果最终落在了temp数组的i+j和i+j+1索引上
     * 例如3和5，i=2，j=0，计算结果15，1落在i+j=2，5落在i+j+1=3
     *
     */
class Solution {
    public String multiply(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int[] temp = new int[length1+length2];

        for (int i = length2-1; i >=0 ; i--) {
            for (int j = length1-1; j >=0 ; j--) {
                int sum = (num2.charAt(i)-'0')*(num1.charAt(j)-'0');
                //i和j的计算结果最终落在了temp数组的i+j和i+j+1索引上
                //填充数组时要考虑数组现有数据，进行求和进位
                sum = temp[i+j+1]+sum;
                //低位
                temp[i+j+1]=sum%10;
                //高位叠加
                temp[i+j]+=sum/10;
            }
        }
        StringBuffer buffer = new StringBuffer();
        boolean flag = true;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i]==0&&flag) {
                continue;
            }else{
                flag=false;
                buffer.append(temp[i]);
            }
        }
        return buffer.length()==0?"0":buffer.toString();

    }
}
//leetcode submit region end(Prohibit modification and deletion)


}