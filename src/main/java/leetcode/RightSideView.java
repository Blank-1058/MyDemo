package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图
 *
 * @author liukang_lc on 2019/4/23
 */
public class RightSideView {

    public static void main(String[] arg0){
        TreeNode node=TreeNode.createTree(new int[]{1,2,3,Integer.MIN_VALUE,5,Integer.MIN_VALUE,4});
        List<Integer> result=rightSideView(node);
    }

    /**
     * 打印层序遍历右边的非空元素
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return  result;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        //每一层的非空元素
        List<Integer> row=new ArrayList<>();
        //当前层的节点数
        int currentNodeNum= 1;
        //下一层的节点数
        int nextNodeNum=0;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            row.add(node.val);
            if(node.left!=null){
                queue.offer(node.left);
                nextNodeNum++;
            }
            if(node.right!=null){
                queue.offer(node.right);
                nextNodeNum++;
            }
            currentNodeNum--;
            if(currentNodeNum==0){
                //当前层的元素全部从队列中拿出
                currentNodeNum=nextNodeNum;
                nextNodeNum=0;
                //取出每一层的最后一个非空元素
                result.add(row.get(row.size()-1));
                row.clear();
            }
        }
        return result;
    }
}
