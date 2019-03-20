package leetcode;

/**
 * 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null){
            return null;
        }
        //先判断两个链表的长度，分别为a和b,且a>b
        //则设置两个指针，一个先走a-b步，然后两个指针同时走，如果相遇则表示相交
        int alength=0,blength=0;
        ListNode longList=headA;
        while(longList!=null){
            longList=longList.next;
            alength++;
        }
        ListNode shortList=headB;
        while(shortList!=null){
            shortList=shortList.next;
            blength++;
        }
        System.out.println("Alength:"+alength);
        System.out.println("BLength:"+blength);
        if(alength<blength){
            longList=headB;
            shortList=headA;
        }else{
            longList=headA;
            shortList=headB;
        }
        int diff=Math.abs(alength-blength);
        System.out.println("diffLength:"+diff);
        System.out.println("longList:"+longList.val);
        System.out.println("shortList:"+shortList.val);
        //长链表先走diff步
        while(diff!=0){
            longList=longList.next;
            diff--;
        }

        while(longList!=null && shortList!=null){
            if(longList==shortList){
                return longList;
            }
            longList=longList.next;
            shortList=shortList.next;

        }
        return null;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
