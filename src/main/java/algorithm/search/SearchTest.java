package algorithm.search;

public class SearchTest {

    public static void main(String[] arg0){
        //二分查找
        int[] array=new int[]{0,1,2,3,4,5,7,8,9,10};
        int result=SearchUtils.binarySearch(array,7);
        System.out.println("binarySearch:"+result);

        //二叉搜索树测试
        BinarySearchTree binarySearchTree=new BinarySearchTree();
        binarySearchTree.insert(4,4);
        binarySearchTree.insert(2,2);
        binarySearchTree.insert(5,5);
        binarySearchTree.insert(1,1);
        binarySearchTree.insert(3,3);
        binarySearchTree.insert(6,6);
        System.out.println("前序遍历");
        binarySearchTree.preOrderTraversal(binarySearchTree.root);
        System.out.println("中序遍历");
        binarySearchTree.inOrderTraversal(binarySearchTree.root);
        System.out.println("后序遍历");
        binarySearchTree.postOrderTraversal(binarySearchTree.root);

    }

}
