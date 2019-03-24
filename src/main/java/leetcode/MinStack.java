package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack {

    private List<Integer> list=null;
    //每存放一个数，就将当前列表的最小值存储起来
    private List<Integer> minList=null;
    /** initialize your data structure here. */
    public MinStack() {
        this.list=new ArrayList<>();
        this.minList=new ArrayList<>();
    }

    public void push(int x) {
        this.list.add(x);
        if(minList.size()==0 || x<=minList.get(minList.size()-1)){
            minList.add(x);
        }else{
            minList.add(minList.get(minList.size()-1));
        }
    }

    public void pop() {
        this.list.remove(this.list.size()-1);
        this.minList.remove(this.minList.size()-1);
    }

    public int top() {
        return this.list.get(this.list.size()-1);
    }

    public int getMin() {
        return this.minList.get(this.minList.size()-1);
    }
}
