package datastructure.linkedlist.demo;

import datastructure.linkedlist.SinglyNodeUtils;
import datastructure.linkedlist.SinglyListNode;

/**
 * 单向链表反转
 *
 * @author liukang_lc on 2019/2/21
 */
public class LinkedListReverse {

    private static SinglyListNode listNode=null;

    public static void main(String[] arg0){
        listNode=SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);
        SinglyListNode node=reverseLinkedList(listNode);
        SinglyNodeUtils.printNode(node);
    }

    private static SinglyListNode reverseLinkedList(SinglyListNode listNode){
        if(listNode==null || listNode.getNext()==null){
            return listNode;
        }
        SinglyListNode nodeTmp1=listNode;
        SinglyListNode nodeTmp2=listNode.getNext();
        nodeTmp1.setNext(null);
        while(nodeTmp2!=null){
            SinglyListNode nodeTmp3=nodeTmp2.getNext();
            nodeTmp2.setNext(nodeTmp1);
            nodeTmp1=nodeTmp2;
            nodeTmp2=nodeTmp3;
        }
        return nodeTmp1;
    }
}
