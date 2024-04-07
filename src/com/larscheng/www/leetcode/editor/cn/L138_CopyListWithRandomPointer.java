//给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。 
//
// 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 
//指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。 
//
// 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random 
//--> y 。 
//
// 返回复制链表的头节点。 
//
// 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示： 
//
// 
// val：一个表示 Node.val 的整数。 
// random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
// 
//
// 你的代码 只 接受原链表的头节点 head 作为传入参数。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 1000
// 
// -10⁴ <= Node.val <= 10⁴ 
// Node.random 为 null 或指向链表中的节点。 
// 
//
// 
//
// Related Topics 哈希表 链表 👍 1351 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

public class L138_CopyListWithRandomPointer{
      
  public static void main(String[] args) {
       Solution solution = new L138_CopyListWithRandomPointer().new Solution();

  }
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    //拼接+拆分
    //先在原始链表中相邻复制，即node1->node1(new)->node2->node2(new)->node3->node3(new)
    //遍历相邻复制后的链表，给新节点设置random，复制节点都在原始节点的next，所以random也在原始节点random的next
    // 例如node1节点对应的复制节点是node1(new)，node1(new).random = node1.random.next
    //双指针拆分原始节点和复制节点形成2条链表，返回复制节点链表
    //O(n)/O(1)
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            //相邻复制
            Node temp = new Node(cur.val);
            temp.next = cur.next;
            cur.next = temp;
            cur = temp.next;
        }
        cur = head;
        while (cur != null) {
            //给复制节点设置random
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head.next;
        Node pre = head;
        Node res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null;

        return res;
    }
    /*public Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node cur = head;
        Node temp = dummy;
        while (cur!=null){
            temp.next = new Node(cur.val);
            //todo temp.random = ...
            cur = cur.next;
            temp = temp.next;
        }
        return dummy.next;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    //哈希表
    //哈希表记录老节点-新节点的映射关系
    //遍历原始链表，从map中获取每个节点的next和random信息进行赋值，最终从map中获取头节点对应的新节点返回
    //O(n)/O(n)
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        //收集 老节点-新节点 map
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        Node oldCur = head;

        while (oldCur != null) {
            Node newNode = map.get(oldCur);
            newNode.next = map.get(oldCur.next);
            newNode.random = map.get(oldCur.random);
            oldCur = oldCur.next;
        }
        return map.get(head);
    }
    /*public Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node cur = head;
        Node temp = dummy;
        while (cur!=null){
            temp.next = new Node(cur.val);
            //todo temp.random = ...
            cur = cur.next;
            temp = temp.next;
        }
        return dummy.next;
    }*/
}

}