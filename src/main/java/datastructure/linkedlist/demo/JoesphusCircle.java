package datastructure.linkedlist.demo;

import datastructure.linkedlist.SinglyListNode;
import datastructure.linkedlist.SinglyNodeUtils;

/**
 * 约瑟夫环
 * 约瑟夫环（约瑟夫问题）是一个数学的应用问题：
 * 已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。
 * 从编号为k的人开始报数，数到m的那个人出列；
 * 他的下一个人又从1开始报数，数到m的那个人又出列；
 * 依此规律重复下去，直到圆桌周围的人全部出列。
 * @author liukang_lc on 2019/2/27
 */
public class JoesphusCircle {
    private static SinglyListNode listNode;

    public static void main(String[] arg0){
        listNode= SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);

        SinglyListNode lastNode=listNode;
        while(lastNode.getNext()!=null){
            lastNode=lastNode.getNext();
        }
        lastNode.setNext(listNode);

        SinglyListNode remainNode=getLastNode(listNode,5);
        System.out.println("最后一个人的编号是："+remainNode.getVal());
    }

    /**
     * 找到约瑟夫环的最后一个人
     * 思路：使用链表，找到第m个节点，删除此节点，直到只剩下一个节点为止
     * @param listNode
     * @param m
     * @return
     */
    private static SinglyListNode getLastNode(SinglyListNode listNode,int m){
        SinglyListNode targetPreNode=listNode;
        SinglyListNode targetNode=null;
        int k=1;
        while(listNode.getNext()!=listNode){
            targetPreNode=listNode;
            //先找到第m-1的节点
            for(int i=0;i<m-2;i++){
                targetPreNode=targetPreNode.getNext();
            }
            //找到第m的节点
            targetNode=targetPreNode.getNext();
            System.out.println("去掉的第"+k+"个人的编号为："+targetNode.getVal());
            //删除第m节点，也就是把第m-1节点的下一个节点指定为第m+1的节点
            targetPreNode.setNext(targetNode.getNext());
            //将第m+1的节点重新设置为起点
            listNode=targetPreNode.getNext();
            k++;
        }
        return listNode;
    }

}
