package leetcode;

public class Test {

    public static void main(String[] arg0){
        MinStack minStack=new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min=minStack.getMin();
        System.out.println(min);
        minStack.pop();
        int top=minStack.top();
        System.out.println(top);
        min=minStack.getMin();
        System.out.println(min);

    }
}
