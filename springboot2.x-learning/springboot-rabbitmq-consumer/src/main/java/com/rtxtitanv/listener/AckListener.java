package com.rtxtitanv.listener;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.listener.AckListener
 * @description 用于手动确认模式的消息监听类，需实现ChannelAwareMessageListener接口
 * @date 2021/4/21 16:23
 */
@Component
public class AckListener implements ChannelAwareMessageListener {

    private static Logger logger = LoggerFactory.getLogger(AckListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String consumerQueue = message.getMessageProperties().getConsumerQueue();
        try {
            String msg = new String(message.getBody());
            int len = msg.length();

            if ("ack-test-queue1".equals(consumerQueue)) {
                logger.info("消息来自的队列：" + consumerQueue);
                logger.info("消息：" + msg);
                logger.info("消息处理开始");
                int i = 10 / len;
                logger.info("消息处理成功");
                logger.info("10除消息长度等于" + i);
            }

            if ("ack-test-queue2".equals(consumerQueue)) {
                logger.info("消息来自的队列：" + consumerQueue);
                logger.info("消息：" + msg);
                logger.info("消息处理开始");
                int i = 10 / len;
                logger.info("消息处理成功");
                logger.info("10除消息长度等于" + i);
            }

            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            logger.error("消息处理失败");
            channel.basicNack(deliveryTag, false, false);
        }
    }
}