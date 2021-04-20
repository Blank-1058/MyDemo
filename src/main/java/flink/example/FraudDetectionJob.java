package flink.example;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.apache.flink.walkthrough.common.source.TransactionSource;

/**
 * @author liukang_lc on 2021/4/12
 */
public class FraudDetectionJob {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Transaction> transactionDataStream = env.addSource(new TransactionSource()).name("transaction");

        DataStream<Alert> alertDataStream = transactionDataStream.keyBy(Transaction::getAccountId).process(new FraudDetector()).name("fraud-detector");

        alertDataStream.addSink(new AlertSink()).name("send-alerts");

        try {
            env.execute("fraud Detection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
