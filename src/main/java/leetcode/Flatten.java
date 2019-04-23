package leetcode;

/**
 * 二叉树展开为链表
 * 给定一个二叉树，"原地"将它展开为链表。
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 *  原地算法：是一种使用小的，固定数量的额外之空间来转换资料的算法。当算法执行时，输入的资料通常会被要输出的部份覆盖掉。
 * @author liukang_lc on 2019/4/23
 */
public class Flatten {

    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        if(root.left!=null && root.right!=null){
            //节点的左右子树都不为空时
            TreeNode tmp=root.left;
            //遍历得到节点左子树的最右子节点
            while(tmp.right!=null){
                tmp=tmp.right;
            }
            //将节点的右子树放到左子树的节点的最后
            tmp.right=root.right;
            //将节点的左子树放到节点的右子树上
            root.right=root.left;
            //将左子树置空
            root.left=null;
        }else if(root.left!=null && root.right==null){
            //当左子树不为空，右子树为空时，直接把左子树放到右子树上
            root.right=root.left;
            //左子树置空
            root.left=null;
        }
    }
}
