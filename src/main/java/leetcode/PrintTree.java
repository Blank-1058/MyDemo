package leetcode;

import java.util.*;

/**
 * 输出二叉树
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 *
 * 示例 1:
 * 输入:
 *      1
 *     /
 *    2
 * 输出:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * 示例 2:
 * 输入:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 *
 * 示例 3:
 * 输入:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * 注意: 二叉树的高度在范围 [1, 10] 中。
 * @author liukang_lc on 2019/4/20
 */
public class PrintTree {

    public static void main(String[] arg0){
        TreeNode root=TreeNode.createTree(new int[]{1,2,3,Integer.MIN_VALUE,4});
        List<List<String>> result=printTree(root);
    }

    /**
     * 先求出树的深度，然后计算出树的最大宽度，即m和n
     * 其中n=2^m-1
     * @param root
     * @return
     */
    public static List<List<String>> printTree(TreeNode root) {
        List<List<String>> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        //先求树的最大深度，即行数
        int m=maxDepth(root);
        //计算列数
        int n= (int) (Math.pow(2,m)-1);
        //存放树的节点在数组中的索引
        List<List<Integer>> index=new ArrayList<>();
        for(int i=1;i<=m;i++){
            //计算每一层第一个节点所在的索引
            int startIndex= (int) (n/Math.pow(2,i));
            List<Integer> rowIndex=new ArrayList<>();
            rowIndex.add(startIndex);
            //计算每一层节点数
            int nodeNum= (int) Math.pow(2,i-1);
            //计算每个节点与每个节点之间索引的增量
            int increment= (int) Math.pow(2,(m-i+1));
            int indexTmp=startIndex;
            for(int j=0;j<nodeNum-1;j++){
                indexTmp+=increment;
                rowIndex.add(indexTmp);
            }
            index.add(rowIndex);
        }
        //对二叉树进行层序遍历
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        //当前层
        int currentRow=1;
        //当前层的节点所在的索引
        List<Integer> currentIndexRow=index.get(currentRow-1);
        //当前层节点数量
        int currentRowNodeNum=currentIndexRow.size();
        //当前层第几个节点
        int currentNode=0;
        String[] rowStr=intStrArray(n);
        while(currentRow<=m){
            TreeNode node=queue.poll();
            int currentIndex=currentIndexRow.get(currentNode);
            if(node!=null){
                rowStr[currentIndex]=String.valueOf(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }else{
                queue.offer(null);
                queue.offer(null);
            }
            currentRowNodeNum--;
            currentNode++;
            if(currentRowNodeNum==0){
                //表示当前层节点已经全部输出，开始下一层
                currentRow++;
                result.add(Arrays.asList(rowStr));
                rowStr=intStrArray(n);
                if(currentRow>m){
                    break;
                }
                currentIndexRow=index.get(currentRow-1);
                currentRowNodeNum=currentIndexRow.size();
                currentNode=0;
            }
        }

        return result;
    }

    /**
     * 求树的最大深度
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
     * 初始化一个空字符串数组
     * @param n
     * @return
     */
    private static String[] intStrArray(int n){
        String[] result=new String[n];
        Arrays.fill(result,"");
        return result;
    }

}
