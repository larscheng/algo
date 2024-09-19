//
// 请你设计并实现一个满足 
// LRU (最近最少使用) 缓存 约束的数据结构。
// 
//
// 
// 实现 
// LRUCache 类：
// 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
// 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 3017 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L146_LruCache{
      
  public static void main(String[] args) {
      L146_LruCache solution = new L146_LruCache();
      solution.test();
  }
  public  void test() {
      LRUCache solution = new L146_LruCache().new LRUCache(2);
      solution.put(1,1);
      solution.put(2,2);
      solution.get(1);
      solution.put(3,3);
      solution.get(2);
      solution.put(4,4);
      solution.get(1);
      solution.get(3);
      solution.get(4);
  }

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {
      //带过期时间的lru
    class ListNode{

        int key;
        int val;

        long expire;
        public ListNode(int key, int val,long expire ) {
            this.key = key;
            this.val = val;
            this.expire = expire;
        }
        public ListNode(int key, int val ) {
            this.key = key;
            this.val = val;
        }
        public ListNode() {}
    }
    LinkedHashMap<Integer,ListNode> cache = new LinkedHashMap<>();
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        ListNode node = cache.remove(key);
        if (node.expire<System.currentTimeMillis()){
            //lazy expire
            return -1;
        }
        cache.put(key, node);
        return node.val;
    }


    public void put(int key, int value) {
        if (cache.remove(key) != null) {
            //head
            cache.put(key, new ListNode(key, value, System.currentTimeMillis() + 10000));
            return;
        }

        if (cache.size() == capacity) {
            for (ListNode node : cache.values()) {
                if (node.expire < System.currentTimeMillis()) {
                    cache.remove(node.key);
                }
            }
            Integer oldKey = cache.keySet().iterator().next();
            cache.remove(oldKey);
        }
        cache.put(key, new ListNode(key, value, System.currentTimeMillis() + 10000));
    }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

class LRUCache2 {
    class ListNode{

        int key;
        int val;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
        public ListNode() {}
    }
    LinkedHashMap<Integer,Integer> cache = new LinkedHashMap<>();
    int capacity;
    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Integer val = cache.remove(key);
        cache.put(key, val);
        return val;
    }


    public void put(int key, int value) {
        if (cache.remove(key) != null) {
            //head
            cache.put(key, value);
            return;
        }
        if (cache.size() == capacity) {
            Integer oldKey = cache.keySet().iterator().next();
            cache.remove(oldKey);
        }
        cache.put(key, value);
    }


}
class LRUCache1 {
    class ListNode{

        int key;
        int val;
        ListNode pre;
        ListNode next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
        public ListNode() {}
    }
    HashMap<Integer,ListNode> map = new HashMap<>();
    ListNode head;
    ListNode tail;
    int size;
    int capacity;
    public LRUCache1(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new ListNode();
        this.tail = new ListNode();
        //dummyhead ⇄ ..... ⇄ dummytail
        //虚拟头
        this.head.next = this.tail;
        //虚拟尾
        this.tail.pre = this.head;
    }

    public int get(int key) {
        ListNode node = map.get(key);
        if (node != null) {
            //最近使用移动到头
            del(node);
            addHead(node);
            return node.val;
        } else {
            return -1;
        }
    }


    public void put(int key, int value) {
        ListNode node = map.get(key);
        if (node != null) {
            node.val = value;
            map.put(key, node);
            //最近使用，移动到头部
            del(node);
            addHead(node);
        } else {
            ListNode newNode = new ListNode(key,value);
            if (size >= capacity) {
                //删除双向链表尾部的节点，最近最少使用
                map.remove(this.tail.pre.key);
                del(this.tail.pre);
                this.size--;
            }
            map.put(key, newNode);
            addHead(newNode);
            this.size++;
        }
    }


    private void del(ListNode listNode) {
        //del
        ListNode pre = listNode.pre;
        ListNode next = listNode.next;
        pre.next = next;
        next.pre = pre;
    }

    private void addHead(ListNode listNode) {
        //dummyhead     ⇄   otherNode   ⇄   dummytail
        //dummyhead ⇄ listNode ⇄ otherNode ⇄ dummytail
        listNode.pre = this.head;
        listNode.next = this.head.next;
        //otherNode.pre
        this.head.next.pre = listNode;
        //dummyhead.next
        this.head.next = listNode;
    }
}
}