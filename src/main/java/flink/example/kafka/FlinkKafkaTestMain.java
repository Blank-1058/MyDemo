package flink.example.kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

/**
 * flink连接kafka示例
 *
 * @author liukang_lc on 2021/5/8
 */
public class FlinkKafkaTestMain {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String topic = "test";

        Properties properties=new Properties();
        properties.setProperty("bootstrap.servers","182.92.92.203:54542");
        properties.setProperty("group.id","0");

        DataStream<String> stringDataStream = env.addSource(new FlinkKafkaConsumer<String>(topic,new SimpleStringSchema(),properties));

        DataStream<String> processStrDataStream = stringDataStream.shuffle().process(new KafkaMsgProcess()).name("kafka-msg-process-test");

        String producerTopic = "test-prod";
        FlinkKafkaProducer<String> producer=new FlinkKafkaProducer<String>(producerTopic,new SimpleStringSchema(),properties);

        processStrDataStream.addSink(producer).name("kafka-msg-sink");

        try {
            env.execute("testFlinkKafka");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
