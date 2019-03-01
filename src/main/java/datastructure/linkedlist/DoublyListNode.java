package datastructure.linkedlist;

/**
 * 双向链表节点
 *
 * @author liukang_lc on 2019/2/21
 */
public class DoublyListNode {
    private int val;
    private DoublyListNode pre;
    private DoublyListNode next;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public DoublyListNode getPre() {
        return pre;
    }

    public void setPre(DoublyListNode pre) {
        this.pre = pre;
    }

    public DoublyListNode getNext() {
        return next;
    }

    public void setNext(DoublyListNode next) {
        this.next = next;
    }
}
