package flink.example;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author liukang_lc on 2021/4/12
 */
public class FraudDetector extends KeyedProcessFunction<Long, Transaction, Alert> {

    private static final Logger LOG = LoggerFactory.getLogger(AlertSink.class);

    private static final double SMALL_AMOUNT = 1.00;
    private static final double LARGE_AMOUNT = 500.00;
    private static final long ONE_MINUTE = 60*1000;

    private transient ValueState<Boolean> flagState;
    private transient ValueState<Long> timerState;

    @Override
    public void open(Configuration parameters) throws Exception {
        ValueStateDescriptor<Boolean> flagDescriptor = new ValueStateDescriptor<Boolean>("flag", Types.BOOLEAN);
        flagState = getRuntimeContext().getState(flagDescriptor);

        ValueStateDescriptor<Long> timerDescriptor = new ValueStateDescriptor<Long>("timer-state",Types.LONG);
        timerState = getRuntimeContext().getState(timerDescriptor);
    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<Alert> out) throws Exception {
        // 到时间之后清空所有的标志位
        timerState.clear();
        flagState.clear();
    }

    @Override
    public void processElement(Transaction transaction, Context context, Collector<Alert> collector) throws Exception {
        // 获取当前状态
        Boolean lastTransactionWasSmall = flagState.value();
        LOG.info(transaction.toString());

        // 判断是否设置了状态
        if(lastTransactionWasSmall!=null){
            if(transaction.getAmount()>LARGE_AMOUNT){
                Alert alert=new Alert();
                alert.setId(transaction.getAccountId());
                collector.collect(alert);
            }
            // 如果不是交易欺诈行为，清空标志位
            cleanUp(context);
        }

        if(transaction.getAmount()<SMALL_AMOUNT){
            // 本次的交易是小额交易的话，则将标志位设置为true
            flagState.update(true);

            // 设置时间状态
            long timer = context.timerService().currentProcessingTime()+ONE_MINUTE;
            context.timerService().registerEventTimeTimer(timer);
            timerState.update(timer);
        }
    }

    private void cleanUp(Context ctx) throws Exception {
        Long timer = timerState.value();
        ctx.timerService().deleteProcessingTimeTimer(timer);

        timerState.clear();
        flagState.clear();
    }
}
