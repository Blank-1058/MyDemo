package datastructure.tree;

import datastructure.tree.demo.TreeTraverse;

import java.util.List;

/**
 * 测试类
 *
 * @author liukang_lc on 2019/3/27
 */
public class TreeTest {

    public static void main(String[] arg0){
        int[] nums=new int[]{1,2,3,4,5,6,7,8,9};
        BinaryTreeNode root=TreeNodeUtil.createTree(nums);
//        TreeTraverse.preorderTraverse(root);
        List<Integer> result=TreeTraverse.preorderTraverseRecursion(root);
        TreeTraverse.printList("************递归前序遍历**************",result);
        result.clear();
        result=TreeTraverse.preorderTraverseNotRecursion(root);
        TreeTraverse.printList("************非递归前序遍历**************",result);
        result.clear();
        result=TreeTraverse.inorderTraverseRecursion(root);
        TreeTraverse.printList("************递归中序遍历**************",result);
        result.clear();
        result=TreeTraverse.inorderTraverseNotRecursion(root);
        TreeTraverse.printList("************非递归中序遍历**************",result);
        result.clear();
        result=TreeTraverse.postorderTraverseRecursion(root);
        TreeTraverse.printList("************递归后序遍历**************",result);
    }
}
