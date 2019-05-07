package leetcode;

/**
 * 翻转等价二叉树
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出
 * 示例：
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：We flipped at nodes with values 1, 3, and 5.
 *         1                   1
 *     /      \            /      \
 *    2        3          3        2
 *   / \     /             \      / \
 *  4   5   6               6    4   5
 *     / \                          / \
 *    7   8                        8   7
 * @author liukang_lc on 2019/5/6
 */
public class FlipEquiv {

    public static void main(String[] arg0){
        TreeNode node1=TreeNode.createTree(new int[]{1,2,3,4,5,6,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,7,8});
        TreeNode node2=TreeNode.createTree(new int[]{1,3,2,Integer.MIN_VALUE,6,4,5,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,8,7});
        flipEquiv(node1,node2);
    }

    /**
     * 思路：递归检查每一层是否符合
     * @param root1
     * @param root2
     * @return
     */
    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1==null && root2==null){
            return true;
        } else if(root1==null && root2!=null){
            return false;
        } else if(root1!=null && root2==null){
            return false;
        }
        //判断当前节点是否相同
        if(root1.val!=root2.val){
            return false;
        }
        //当前节点的有子树为空时
        boolean nullLeft1=root1.left==null;
        boolean nullRight1=root1.right==null;
        boolean nullLeft2=root2.left==null;
        boolean nullRight2=root2.right==null;
        if(nullLeft1 && !nullRight1 && nullLeft2 && !nullRight2){
            //当前节点左子树全部为空，右子树全部不为空
            if(root1.right.val==root2.right.val){
                return flipEquiv(root1.right,root2.right);
            }else{
                return false;
            }
        }else if(!nullLeft1 && nullRight1 && !nullLeft2 && nullRight2){
            //当前节点的左子树全部不为空，右子树全部为空
            if(root1.left.val==root2.left.val){
                return flipEquiv(root1.left,root2.left);
            }else{
                return false;
            }
        }else if(!nullLeft1 && nullRight1 && nullLeft2 && !nullRight2){
            //第一个节点左子树节点不为空，右子树节点为空，第二个节点右子树节点不为空，左子树节点为空
            if(root1.left.val==root2.right.val){
                return flipEquiv(root1.left,root2.right);
            }else{
                return false;
            }
        }else if(nullLeft1 && !nullRight1 && !nullLeft2 && nullRight2){
            //第一个节点左子树节点为空，右子树节点不为空，第二个节点右子树节点为空，左子树节点不为空
            if(root1.right.val==root2.left.val){
                return flipEquiv(root1.right,root2.left);
            }else{
                return false;
            }
        }else if(!nullLeft1 && !nullRight1 && !nullLeft2 && !nullRight2){
            //子树节点全部不为空
            //判断当前节点是否进行了反转
            if(root1.left.val==root2.right.val && root1.right.val==root2.left.val){
                //当前节点进行了反转，继续对当前节点的子树节点进行判断
                return flipEquiv(root1.left,root2.right) && flipEquiv(root1.right,root2.left);
            }else if(root1.left.val==root2.left.val && root1.right.val==root2.right.val){
                //当前节点没有进行反转，继续对当前节点的子树节点进行判断
                return flipEquiv(root1.left,root2.left) && flipEquiv(root1.right,root2.right);
            }else{
                //子节点并不对称，直接返回false
                return false;
            }
        }else if(nullLeft1 && nullRight1 && nullLeft2 && nullRight2){
            //子节点全部为空
            return true;
        }else{
            return false;
        }
    }
}
