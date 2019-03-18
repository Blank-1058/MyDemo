package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge {

    public static void main(String[] arg0){
        List<Interval> intervals=new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(15,18));
        intervals.add(new Interval(2,6));

        List<Interval> result=merge(intervals);
        System.out.println(result.size());
    }

    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals.isEmpty() || intervals.size()==1){
            return intervals;
        }
//        //对开始元素从小到大排序
        Interval[] intervalsArray=new Interval[intervals.size()];
        intervals=sort(intervals.toArray(intervalsArray));
        List<Interval> result=new ArrayList<>();
        int start=intervals.get(0).start;
        int end=intervals.get(0).end;
        for(int j=1;j<intervals.size();j++){
            if(end>=intervals.get(j).start){
                end=Math.max(end,intervals.get(j).end);
            }else{
                result.add(new Interval(start,end));
                start=intervals.get(j).start;
                end=intervals.get(j).end;
            }
        }
        result.add(new Interval(start,end));
        return result;
    }

    private static List<Interval> sort(Interval[] intervals){
        //使用选择排序
        for(int i=0;i<intervals.length;i++){
            int minIndex=i;
            for(int j=i+1;j<intervals.length;j++){
                if(intervals[j].start<intervals[minIndex].start){
                    minIndex=j;
                }
            }
            Interval tmp=intervals[minIndex];
            intervals[minIndex]=intervals[i];
            intervals[i]=tmp;
        }
        return Arrays.asList(intervals);
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
