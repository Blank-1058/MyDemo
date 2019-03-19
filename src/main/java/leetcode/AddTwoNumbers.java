package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    public static void main(String[] arg0){
        ListNode node=new ListNode(3);
        node.next=new ListNode(7);
        ListNode l2=new ListNode(9);
        l2.next=new ListNode(2);

        ListNode result=addTwoNumbers(node,l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result=new ListNode(-1);
        ListNode tmp=result;
        //进位
        int carryNum=0;
        while(l1!=null && l2!=null){
            int sum=l1.val+l2.val+carryNum;
            tmp.next=new ListNode(sum%10);
            carryNum=sum/10;
            tmp=tmp.next;
            l1=l1.next;
            l2=l2.next;
        }
        //处理进位问题
        while(l1!=null){
            int sum=l1.val + carryNum;
            l1.val = sum%10;
            carryNum=sum/10;
            tmp.next=new ListNode(l1.val);
            tmp=tmp.next;
            l1=l1.next;
        }
        //处理进位问题
        while(l2!=null){
            int sum=l2.val + carryNum;
            l2.val = sum%10;
            carryNum=sum/10;
            tmp.next=new ListNode(l2.val);
            tmp=tmp.next;
            l2=l2.next;
        }
        if(carryNum==1){
            tmp.next=new ListNode(carryNum);
        }
        return result.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
