package flink.example.kafka;

import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

/**
 * kafka消息处理
 *
 * @author liukang_lc on 2021/5/10
 */
public class KafkaMsgProcess extends ProcessFunction<String,String> {
    @Override
    public void processElement(String s, Context context, Collector<String> collector) throws Exception {
        String out  = s+"--out";
        System.err.println(out);
        collector.collect(out);
    }
}
