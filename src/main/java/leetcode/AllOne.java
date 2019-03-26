package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 全 O(1) 的数据结构
 *
 * 实现一个数据结构支持以下操作：
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * 挑战：以 O(1) 的时间复杂度实现所有操作。
 *
 * @author liukang_lc on 2019/3/25
 */
public class AllOne {

    /**
     * 头节点和尾节点，按照数值从大到小的顺序排列
     */
    private Node head;
    private Node tail;
    /**
     * 存储已经放入的数据
     */
    private HashMap<String,Node> map;

    /** Initialize your data structure here. */
    public AllOne() {
        head=new Node();
        tail=new Node();
        map=new HashMap<>();
        head.next=tail;
        tail.pre=head;
        //头尾节点的数值初始化为-1
        head.value=-1;
        tail.value=-1;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(key.isEmpty()){
            return;
        }
        if(map.containsKey(key)){
            //如果存在key值，在原key对应的数值的基础上加1，并调整key值所在的节点
            Node currentNode=map.get(key);
            currentNode.set.remove(key);
            if(currentNode.pre.value==currentNode.value+1){
                //如果前一个节点的数值正好为当前节点数值+1，则把当前key值直接放入前一个节点
                currentNode.pre.set.add(key);
                map.put(key,currentNode.pre);
            }else{
                //如果前一个节点的数值不等于当前节点数值+1，需要创建一个新的节点插入到当前节点和前一个节点之间
                Node newNode=new Node();
                newNode.value=currentNode.value+1;
                newNode.set.add(key);

                currentNode.pre.next=newNode;
                newNode.pre=currentNode.pre;

                currentNode.pre=newNode;
                newNode.next=currentNode;
                map.put(key,newNode);
            }
            if(currentNode.set.size()==0){
                //处理之后如果当前节点已经不存在元素，则删除当前节点
                currentNode.pre.next=currentNode.next;
                currentNode.next.pre=currentNode.pre;
            }
        }else{
            //如果不存在key值,需要进行添加
            Node minNode=tail.pre;
            if(minNode.value==-1){
                //当数值为-1时，说明链表中还没有元素，则添加第一个元素
                minNode=new Node();
                minNode.value=1;
                minNode.set.add(key);
                tail.pre=minNode;
                minNode.next=tail;
                head.next=minNode;
                minNode.pre=head;
                map.put(key,minNode);
            }else{
                if(minNode.value==1){
                    //当最小节点的数值为1时，直接将key值加入到节点中就行
                    minNode.set.add(key);
                    map.put(key,minNode);
                }else{
                    //当最小节点的数值不为1时，直接在尾部与最小节点之间插入一个新的节点
                    Node newNode=new Node();
                    newNode.value=1;
                    newNode.set.add(key);
                    minNode.next=newNode;
                    newNode.pre=minNode;
                    newNode.next=tail;
                    tail.pre=newNode;
                    map.put(key,newNode);
                }
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(key.isEmpty()){
            return;
        }
        if(map.containsKey(key)){
            //只有当包含此key时，才会执行此函数
            Node currentNode=map.get(key);
            currentNode.set.remove(key);
            if(currentNode.value-1==0){
                //当此节点数值为1时，执行dec操作会使得此key对应的数值为0，则删除此key
                map.remove(key);
            }else{
                //当此节点数值不为1时，执行dec操作导致数值减1,将此key移动到其他节点
                if(currentNode.next.value==currentNode.value-1){
                    //如果下一节点的数值正好为当前数值减1，把key直接放到下一节点
                    currentNode.next.set.add(key);
                    map.put(key,currentNode.next);
                }else{
                    //如果下一节点的数值不是当前数值减1，则新建一个节点，插入到当前节点与下一节点之间
                    Node newNode=new Node();
                    newNode.value=currentNode.value-1;
                    newNode.set.add(key);

                    newNode.next=currentNode.next;
                    currentNode.next.pre=newNode;

                    newNode.pre=currentNode;
                    currentNode.next=newNode;

                    map.put(key,newNode);
                }
            }
            if(currentNode.set.size()==0){
                //操作之后，如果节点中已经不存在key值，删除节点
                currentNode.pre.next=currentNode.next;
                currentNode.next.pre=currentNode.pre;
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        Node maxNode=head.next;
        if(maxNode.value==-1 || maxNode.set.size()==0){
            return "";
        }

        String[] keys=new String[ maxNode.set.size()];
        keys=maxNode.set.toArray(keys);
        return keys[0];
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        Node minNode=tail.pre;
        if(minNode.value==-1 || minNode.set.size()==0){
            return "";
        }
        String[] keys=new String[ minNode.set.size()];
        keys=minNode.set.toArray(keys);
        return keys[0];
    }

    private class Node{
        /**
         * key值对应的数量
         */
        int value;
        /**
         * 相同数量的key值集合
         */
        HashSet<String> set;
        Node pre;
        Node next;

        public Node() {
            set=new HashSet<>();
        }
    }
}
