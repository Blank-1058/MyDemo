package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 * LRUCache cache = new LRUCache( 2 //缓存容量 );
 *cache.put(1,1);
 *cache.put(2,2);
 *cache.get(1);       // 返回  1
 *cache.put(3,3);    // 该操作会使得密钥 2 作废
 *cache.get(2);       // 返回 -1 (未找到)
 *cache.put(4,4);    // 该操作会使得密钥 1 作废
 *cache.get(1);       // 返回 -1 (未找到)
 *cache.get(3);       // 返回  3
 *cache.get(4);       // 返回  4
 */
public class LRUCache {

    /**
     * 容量
     */
    private int capacity;
    /**
     * 当前容量
     */
    private int currentSize;
    /**
     * 头节点，其nextNode为最新使用的数据
     */
    private Node nodeHead;
    /**
     * 尾节点，其preNode为最早使用的数据
     */
    private Node nodeTail;
    /**
     * 存储key值对应关系
     */
    private Map<Integer,Node> lruMap;

    public LRUCache(int capacity) {
        this.currentSize=0;
        this.capacity=capacity;
        nodeHead=new Node();
        nodeTail=new Node();
        lruMap=new HashMap<>();
    }

    public int get(int key) {
        if(lruMap.containsKey(key)){
            //获取key对应的值，并将此节点放置到头节点的后方
            Node currentNode=lruMap.get(key);
            if(currentSize!=1){
                //当只有一个节点时，不做任何处理，只有包含多个节点时，才会执行操作
                setRecentNode(currentNode);
            }
           return currentNode.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(currentSize==0 && capacity>0){
            Node currentNode=new Node(key,value);
            lruMap.put(key,currentNode);
            nodeHead.nextNode=currentNode;
            nodeTail.preNode=currentNode;
            currentNode.nextNode=nodeTail;
            currentNode.preNode=nodeHead;
            currentSize++;
            return;
        }
        //判断是否已经存在，如果已经存在，直接替换并放置到头节点处
        if(lruMap.containsKey(key)){
            Node currentNode=lruMap.get(key);
            currentNode.value=value;
            if(currentSize!=1){
                //如果包含多个节点，替换当前节点值，并把此节点放置到头节点之后
                setRecentNode(currentNode);
            }
        }else{
            //如果不存在，添加数据到头节点处
            Node currentNode=new Node(key,value);
            if(currentSize<capacity){
                //当前数据结构大小没有达到容量时，直接添加
                addNode(currentNode);
                currentSize++;
            }else{
                //已经达到容量之后，删除尾节点的前一个节点，然后将当前节点添加到头节点之后
                deleteNode();
                addNode(currentNode);
            }
        }
    }

    /**
     * 将当前节点直接放置到头节点后，其他节点依次后移
     * @param currentNode
     */
    private void setRecentNode(Node currentNode){
        if(currentNode.preNode==nodeHead){
            //如果本来就在头节点之后，不需要操作
            return;
        }
        Node currentPreNode=currentNode.preNode;
        Node currentNextNode=currentNode.nextNode;
        Node headNextNode=nodeHead.nextNode;

        nodeHead.nextNode=currentNode;
        currentNode.preNode=nodeHead;

        currentNode.nextNode=headNextNode;
        headNextNode.preNode=currentNode;

        currentPreNode.nextNode=currentNextNode;
        currentNextNode.preNode=currentPreNode;
    }

    /**
     * 添加节点到头节点之后
     * @param currentNode
     */
    private void addNode(Node currentNode){
        Node headNextNode=nodeHead.nextNode;
        currentNode.preNode=nodeHead;
        nodeHead.nextNode=currentNode;
        currentNode.nextNode=headNextNode;
        headNextNode.preNode=currentNode;
        lruMap.put(currentNode.key,currentNode);
    }

    /**
     * 删除尾节点之前的节点
     */
    private void deleteNode(){
        Node tailPreNode=nodeTail.preNode;
        nodeTail.preNode=tailPreNode.preNode;
        tailPreNode.preNode.nextNode=nodeTail;
        lruMap.remove(tailPreNode.key);
    }

    private class Node{
        int key;
        int value;
        Node preNode;
        Node nextNode;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
