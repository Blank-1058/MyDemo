package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 * 输入:
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 *
 * 示例 2:
 * 输入:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 *
 * 示例 3:
 * 输入:
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 *
 * 示例 4:
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 * @author liukang_lc on 2019/4/22
 */
public class WidthOfBinaryTree {

    public static void main(String[] arg0){
        TreeNode root=TreeNode.createTree(new int[]{1,1,1,1,1,1,1,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,1,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,2,2,2,2,2,2,2,Integer.MIN_VALUE,2,Integer.MIN_VALUE,Integer.MIN_VALUE,2,Integer.MIN_VALUE,2});
        int result=widthOfBinaryTree(root);
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        int maxDepth=maxDepth(root);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        //当前层数
        int currentRow=1;
        //对于满二叉树来说当前层的节点数
        int currentNodeNum= (int) Math.pow(2,currentRow-1);
        int maxWidth=0;
        //存放当前层的节点值，为null则表示无节点
        List<Integer> rowNodes=new ArrayList<>();
        while(currentRow<=maxDepth){
            TreeNode node=queue.poll();
            if(node!=null){
                rowNodes.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }else{
                rowNodes.add(null);
                queue.offer(null);
                queue.offer(null);
            }
            currentNodeNum--;
            if(currentNodeNum==0){
                //表示当前层已经没有节点，进入下一层
                currentRow++;
                currentNodeNum=(int) Math.pow(2,currentRow-1);
                maxWidth=Math.max(maxWidth,getWidth(rowNodes));
                rowNodes.clear();
            }
        }
        return maxWidth;
    }

    /**
     * 计算树的最大深度
     * @param node
     * @return
     */
    private static int maxDepth(TreeNode node){
        if(node==null){
            return 0;
        }
        int left=maxDepth(node.left);
        int right=maxDepth(node.right);
        return Math.max(left,right)+1;
    }

    /**
     * 计算每一层的宽度
     * 即去掉列表前后为空的节点后的列表长度
     * @param nodes
     * @return
     */
    private static int getWidth(List<Integer> nodes){
        int startIndex=0;
        int endIndex=nodes.size()-1;
        //找出列表开始不为空的索引位置
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i)!=null){
                startIndex=i;
                break;
            }
        }
        //找出列表结尾不为空的索引位置
        for(int i=nodes.size()-1;i>=0;i--){
            if(nodes.get(i)!=null){
                endIndex=i;
                break;
            }
        }
        return endIndex-startIndex+1;
    }
}
