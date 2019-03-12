package algorithm.search;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    /**
     * 根节点
     */
    BinarySearchTreeNode root;

    /**
     * 根据索引查找结点
     * @param index
     * @return
     */
    public BinarySearchTreeNode get(int index){
        BinarySearchTreeNode currentNode=root;
        while(currentNode!=null && currentNode.getIndex()!=index){
            if(currentNode.getIndex()>index){
                currentNode=currentNode.getLeft();
            }else{
                currentNode=currentNode.getRight();
            }
        }
        return currentNode;
    }

    /**
     * 插入节点
     * @param index
     * @param value
     * @return
     */
    public void insert(int index,int value){
        if(root==null){
            root=new BinarySearchTreeNode(index, value);
            return;
        }
        BinarySearchTreeNode currentNode=root;
        BinarySearchTreeNode parentNode=root;
        boolean isLeft=true;
        while(currentNode!=null){
            parentNode=currentNode;
            if(currentNode.getIndex()>index){
                currentNode=currentNode.getLeft();
                isLeft=true;
            }else if(currentNode.getIndex()<index){
                currentNode=currentNode.getRight();
                isLeft=false;
            }else{
                //因为二叉搜索树不存在键值相等的节点，所以键值相同时，替换掉原来的值
                currentNode.setValue(value);
                return;
            }
        }
        BinarySearchTreeNode newNode=new BinarySearchTreeNode(index,value);
        if(isLeft){
            parentNode.setLeft(newNode);
        }else{
            parentNode.setRight(newNode);
        }
    }

    /**
     * 删除指定节点
     * @param index
     * @return
     */
    public boolean deleteNode(int index){
        return false;
    }


}
