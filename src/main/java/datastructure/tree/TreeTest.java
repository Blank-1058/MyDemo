package datastructure.tree;

import datastructure.tree.demo.TreeTraverse;

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
        TreeTraverse.inorderTraverse(root);
    }
}
