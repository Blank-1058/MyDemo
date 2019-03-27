package datastructure.tree.demo;

import datastructure.tree.BinaryTreeNode;

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
    public static void preorderTraverse(BinaryTreeNode root){
        if(root==null){
            System.out.println("tree is null");
        }
        System.out.println("****前序遍历递归实现****");
        System.out.print("[");
        preorderTraverseRecursion(root);
        System.out.print("]");
        System.out.println();

        System.out.println("****前序遍历非递归实现****");
        System.out.print("[");
        preorderTraverseNotRecursion(root);
        System.out.print("]");
        System.out.println();


    }

    /**
     * 用递归方式实现前序遍历
     * @param node
     */
    private static void preorderTraverseRecursion(BinaryTreeNode node){
        if(node==null){
            return;
        }
        System.out.print(node.getVal()+",");
        preorderTraverseRecursion(node.getLeftNode());
        preorderTraverseRecursion(node.getRightNode());
    }

    /**
     * 非递归方式实现前序遍历
     * @param node
     */
    private static void preorderTraverseNotRecursion(BinaryTreeNode node){
        Stack<BinaryTreeNode> stack=new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            BinaryTreeNode parent=stack.pop();
            System.out.print(parent.getVal()+",");
            if(parent.getRightNode()!=null){
                stack.push(parent.getRightNode());
            }
            if(parent.getLeftNode()!=null){
                stack.push(parent.getLeftNode());
            }
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void inorderTraverse(BinaryTreeNode root){
        if(root==null){
            System.out.println("tree is null");
        }
        System.out.println("****中序遍历递归实现****");
        System.out.print("[");
        inorderTraverseRecursion(root);
        System.out.print("]");
        System.out.println();

//        System.out.println("****中序遍历非递归实现****");
//        System.out.print("[");
//        inorderTraverseNotRecursion(root);
//        System.out.print("]");
//        System.out.println();
    }

    /**
     * 用递归方式实现中序遍历
     * @param node
     */
    private static void inorderTraverseRecursion(BinaryTreeNode node){
        if(node==null){
            return;
        }
        inorderTraverseRecursion(node.getLeftNode());
        System.out.print(node.getVal()+",");
        inorderTraverseRecursion(node.getRightNode());
    }

    /**
     * 用非递归方式实现中序遍历
     * @param node
     */
    private static void inorderTraverseNotRecursion(BinaryTreeNode node){

    }
}
