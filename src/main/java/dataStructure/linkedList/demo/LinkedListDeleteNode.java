package dataStructure.linkedList.demo;

import dataStructure.linkedList.SinglyListNode;
import dataStructure.linkedList.SinglyNodeUtils;

/**
 * 不遍历链表删除非尾节点
 *
 * @author liukang_lc on 2019/2/27
 */
public class LinkedListDeleteNode {

    private static SinglyListNode listNode;

    public static void main(String[] arg0){
        listNode=SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);

        //要删除的节点
        SinglyListNode deleteNode=listNode.getNext().getNext().getNext().getNext();
        System.out.println("deleteNode:"+deleteNode.getVal());

        deleteNode(deleteNode);

        SinglyNodeUtils.printNode(listNode);
    }

    /**
     * 在不遍历链表的情况下删除指定非尾节点
     * 思路：
     * 找到要删除的节点下一节点，然后把数据复制到要删除的节点中，删除下一节点
     * @param deleteNode
     * @return
     */
    private static void deleteNode(SinglyListNode deleteNode){
        SinglyListNode nextNode=deleteNode.getNext();
        deleteNode.setVal(nextNode.getVal());
        deleteNode.setNext(nextNode.getNext());
    }
}
