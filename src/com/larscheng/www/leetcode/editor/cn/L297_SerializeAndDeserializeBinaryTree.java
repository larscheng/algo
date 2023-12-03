//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 1184 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L297_SerializeAndDeserializeBinaryTree{
      
  public static void main(String[] args) {
      Codec solution = new L297_SerializeAndDeserializeBinaryTree().new Codec();

  }
 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    public String serialize(TreeNode root) {
        return encode(root, "");
    }

    public String encode(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += root.val + ",";
            str = encode(root.left, str);
            str = encode(root.right, str);
        }
        return str;
    }


    public TreeNode deserialize(String data) {
        List<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
        return decode(list);
    }
    public TreeNode decode(List<String> dataList) {
        String val = dataList.get(0);
        if ("null".equals(val)) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));
        dataList.remove(0);
        root.left = decode(dataList);
        root.right = decode(dataList);

        return root;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
public class Codec1 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return encode(root, "");
    }

    private String encode(TreeNode root, String encode) {
        if (root == null) {
            return encode + "null,";
        }
        encode += root.val+",";

        return encode(root.left, encode) + encode(root.right, encode);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
        //List<String> list1 = new LinkedList<String>(Arrays.asList(data.split(",")));
        return decode(list);
    }

    private TreeNode decode(List<String> list) {
        String val = list.get(0);

        //String val = list.remove(0);
        if ("null".equals(val)){
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        list.remove(0);
        root.left = decode(list);
        root.right = decode(list);
        return root;
    }
}


}