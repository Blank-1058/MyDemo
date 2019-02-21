package dataStructure.linkedList.demo;

import dataStructure.linkedList.SinglyListNode;
import dataStructure.linkedList.SinglyNodeUtils;

/**
 * 查找链表的倒数第K个节点
 *
 * @author liukang_lc on 2019/2/21
 */
public class LinkedListKthFinder {

    private static SinglyListNode listNode=null;

    public static void main(String[] arg0){
        listNode=SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);
        SinglyListNode node=findKth(listNode,3);
        if(node==null){
            System.out.println("find failure");
        }else{
            System.out.println("Kth:"+node.getVal());
        }

    }

    /**
     * 查找链表的倒数第K的节点
     * 思路：设置两个指针，一个快指针，一个慢指针
     *      快指针先走k-1步，然后两个指针同时前进
     *      当快指针走到链表结尾时，慢指针所在的位置就是倒数第k个指针
     * @param listNode
     * @param k
     * @return
     */
    private static SinglyListNode findKth(SinglyListNode listNode,int k){
        SinglyListNode fast=listNode;
        SinglyListNode slow=listNode;
        for(int i=0;i<k-1;i++){
            fast=fast.getNext();
            if(fast==null){
                return null;
            }
        }
        while(fast.getNext()!=null){
            fast=fast.getNext();
            slow=slow.getNext();
        }
        return slow;
    }
}
