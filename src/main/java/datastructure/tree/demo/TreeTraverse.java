package datastructure.tree.demo;

import datastructure.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树遍历
 *
 * @author liukang_lc on 2019/3/2
 */
public class TreeTraverse {

    /**
     * 前序遍历
     * @param root
     */
    public static List<Integer> preorderTraverseRecursion(BinaryTreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null){
            System.out.println("tree is null");
            return result;
        }
        preorderTraverseRecursion(root,result);
        return result;
    }

    /**
     * 用递归方式实现前序遍历
     * @param node
     */
    private static void preorderTraverseRecursion(BinaryTreeNode node,List<Integer> result){
        if(node==null){
            return;
        }
        result.add(node.getVal());
        preorderTraverseRecursion(node.getLeftNode(),result);
        preorderTraverseRecursion(node.getRightNode(),result);
    }

    /**
     * 非递归方式实现前序遍历
     * @param root
     */
    public static List<Integer> preorderTraverseNotRecursion(BinaryTreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null){
            System.out.println("tree is null");
            return result;
        }
        Stack<BinaryTreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            BinaryTreeNode parent=stack.pop();
            result.add(parent.getVal());
            if(parent.getRightNode()!=null){
                stack.push(parent.getRightNode());
            }
            if(parent.getLeftNode()!=null){
                stack.push(parent.getLeftNode());
            }
        }
        return result;
    }

    /**
     * 中序遍历
     * @param root
     */
    public static List<Integer> inorderTraverseRecursion(BinaryTreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        inorderTraverseRecursion(root,result);
        return result;
    }

    /**
     * 用递归方式实现中序遍历
     * @param node
     */
    private static void inorderTraverseRecursion(BinaryTreeNode node,List<Integer> result){
        if(node==null){
            return;
        }
        inorderTraverseRecursion(node.getLeftNode(),result);
        result.add(node.getVal());
        inorderTraverseRecursion(node.getRightNode(),result);
    }

    /**
     * 用非递归方式实现中序遍历
     * @param root
     */
    public static List<Integer> inorderTraverseNotRecursion(BinaryTreeNode root){
        List<Integer> result=new ArrayList<>();
        Stack<BinaryTreeNode> stack=new Stack<>();
        BinaryTreeNode tmp=root;
        while(!stack.empty() || tmp!=null){
            while(tmp!=null){
                stack.push(tmp);
                tmp=tmp.getLeftNode();
            }
            if(!stack.empty()){
                //当某个节点的左子节点为空时，使该节点出栈，然后访问此节点的右子节点
                tmp=stack.pop();
                result.add(tmp.getVal());
                tmp=tmp.getRightNode();
            }
        }
        return result;
    }

    /**
     * 用递归方式实现后序遍历
     * @param root
     * @return
     */
    public static List<Integer> postorderTraverseRecursion(BinaryTreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        postorderTraverseRecursion(root,result);
        return result;
    }

    /**
     * 用递归方式实现后序遍历
     * @param node
     */
    private static void postorderTraverseRecursion(BinaryTreeNode node,List<Integer> result){
        if(node==null){
            return;
        }
        postorderTraverseRecursion(node.getLeftNode(),result);
        postorderTraverseRecursion(node.getRightNode(),result);
        result.add(node.getVal());
    }

    /**
     * 用非递归方式实现后序遍历
     * @param root
     */
    public static List<Integer> postorderTraverseNotRecursion(BinaryTreeNode root){
        List<Integer> result=new ArrayList<>();
        Stack<BinaryTreeNode> stack=new Stack<>();
        //当前访问的节点
        BinaryTreeNode curNode=root;
        //上一次访问的节点
        BinaryTreeNode lastNode=null;
        while(!stack.empty()){
            while(curNode!=null){
                stack.push(curNode);
                lastNode=curNode;
                curNode=curNode.getLeftNode();
            }
        }
        return result;
    }

    /**
     * 打印list结果
     * @param message
     * @param list
     */
    public static void printList(String message,List<Integer> list){
        System.out.println(message);
        StringBuilder printStr=new StringBuilder("[");
        for(int i:list){
            printStr.append(i).append(",");
        }
        printStr.deleteCharAt(printStr.length()-1);
        printStr.append("]");
        System.out.println(printStr);
    }
}
