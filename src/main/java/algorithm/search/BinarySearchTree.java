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
        //查找到要删除的节点
        BinarySearchTreeNode currentNode=root;
        BinarySearchTreeNode parentNode=root;
        boolean isLeft=true;
        while(currentNode!=null && currentNode.getIndex()!=index){
            parentNode=currentNode;
            if(currentNode.getIndex()>index){
                currentNode=currentNode.getLeft();
                isLeft=true;
            }else if(currentNode.getIndex()<index){
                currentNode=currentNode.getRight();
                isLeft=false;
            }else{
                break;
            }
        }
        if(currentNode==null){
            return false;
        }
        if(currentNode.getLeft()==null && currentNode.getRight()==null){
            //当要删除的节点为子节点时，直接删除此节点即可
            if(isLeft){
                parentNode.setLeft(null);
            }else{
                parentNode.setRight(null);
            }
        }else if(currentNode.getLeft()==null){
            //待删除节点只有右子节点时
            if(currentNode==root){
                root=currentNode.getRight();
            }else if(isLeft){
                //如果待删除节点是其父节点的左子节点
                //则将其父节点的左子节点替换为待删除节点的右子节点
                parentNode.setLeft(currentNode.getRight());
            }else{
                //如果待删除节点是其父节点的右子节点
                //则将其父节点的右子节点替换为待删除节点的右子节点
                parentNode.setRight(currentNode.getRight());
            }
        }else if(currentNode.getRight()==null){
            //待删除节点只有左子节点时
            if(currentNode==root){
                root=currentNode.getLeft();
            }else if(isLeft){
                //如果待删除节点是其父节点的左子节点
                //则将其父节点的左子节点替换为待删除节点的左子节点
                parentNode.setLeft(currentNode.getLeft());
            }else{
                //如果待删除节点是其父节点的右子节点
                //则将其父节点的右子节点替换为待删除节点的左子节点
                parentNode.setRight(currentNode.getLeft());
            }
        }else{
            //待删除节点有两个节点时，找到待删除节点的中序遍历后继节点
            //然后将此后继节点删除，并将此节点的index和value复制到待删除节点中即可
            BinarySearchTreeNode directPostNode=getDirectPostNode(currentNode);
            currentNode.setIndex(directPostNode.getIndex());
            currentNode.setValue(directPostNode.getValue());
        }
        return true;
    }

    /**
     * 获取待删除节点的中序遍历后继节点
     * @param delNode
     * @return
     */
    private BinarySearchTreeNode getDirectPostNode(BinarySearchTreeNode delNode){

        BinarySearchTreeNode parentNode=delNode;
        BinarySearchTreeNode directPostNode=delNode;
        BinarySearchTreeNode currentNode=delNode.getRight();
        //找到待删除节点的中序遍历后继节点directPostNode
        while(currentNode!=null){
            parentNode=directPostNode;
            directPostNode=currentNode;
            currentNode=currentNode.getLeft();
        }
        //删除此后继节点，然后将此后继节点的右子节点放到此后继节点父节点的左子节点处替代此后继节点
        if(directPostNode!=delNode.getRight()){
            parentNode.setLeft(directPostNode.getRight());
            directPostNode.setRight(null);
        }else{
            //当后继节点为待删除节点的右子节点时，直接删除右子节点
            parentNode.setRight(directPostNode.getRight());
            directPostNode.setRight(null);
        }
        return directPostNode;
    }

    /**
     * 前序遍历
     * 父节点->左子节点->右子节点
     * @param root
     */
    public void preOrderTraversal(BinarySearchTreeNode root){
        if(root!=null){
            System.out.println(root.getIndex()+":"+root.getValue());
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());
        }
    }

    /**
     * 中序遍历
     * 左子节点->父节点->右子节点
     * @param root
     */
    public void inOrderTraversal(BinarySearchTreeNode root){
        if(root!=null){
            inOrderTraversal(root.getLeft());
            System.out.println(root.getIndex()+":"+root.getValue());
            inOrderTraversal(root.getRight());
        }
    }

    /**
     * 后序遍历
     * 左子节点->右子节点->父节点
     * @param root
     */
    public void postOrderTraversal(BinarySearchTreeNode root){
        if(root!=null){
            postOrderTraversal(root.getLeft());
            postOrderTraversal(root.getRight());
            System.out.println(root.getIndex()+":"+root.getValue());
        }
    }


}
