package leetcode;

import java.util.Stack;

/**
 * 把二叉搜索树转换为累加树
 *
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 例如：
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 *
 */
public class ConvertBST {

    public static void main(String[] arg0){
        TreeNode test=TreeNode.createTree(new int[]{5,2,13});
        test=convertBSTRecursion(test);
    }

    /** 思路：从右子树开始的中序遍历
     * @param root
     * @return
     */
    public static TreeNode convertBSTRecursion(TreeNode root) {
        if(root==null){
            return null;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode tmp=root;
        int current=0;
        while(!stack.empty() || tmp!=null){
            while(tmp!=null){
                stack.push(tmp);
                tmp=tmp.right;
            }
            if(!stack.empty()){
                tmp=stack.pop();
                tmp.val+=current;
                current=tmp.val;
                tmp=tmp.left;
            }
        }
        return root;
    }

}
