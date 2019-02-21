package dataStructure.linkedList.demo;

import dataStructure.linkedList.SinglyListNode;
import dataStructure.linkedList.SinglyNodeUtils;

/**
 * 查找单向链表的中间节点
 *
 * @author liukang_lc on 2019/2/21
 */
public class LinkedListCenterFinder {

    private static SinglyListNode listNode;

    public static void main(String[] arg0){
        listNode=SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);
        SinglyListNode node=findCenterNode(listNode);
        System.out.println("center:"+node.getVal());
    }

    /**
     * 查找链表的中间节点
     * 思路：设置一个快指针，一次走两步，一个慢指针，一次走一步，当快指针走到链表结束时，慢指针正好位于链表中间位置
     * @param listNode
     * @return
     */
    private static SinglyListNode findCenterNode(SinglyListNode listNode){
        if(listNode ==null || listNode.getNext()==null){
            return listNode;
        }
        SinglyListNode fast=listNode;
        SinglyListNode slow=listNode;
        while(fast.getNext()!=null && fast.getNext().getNext()!=null){
            fast=fast.getNext().getNext();
            slow=slow.getNext();
        }
        return slow;
    }
}
