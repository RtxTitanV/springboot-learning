package com.rtxtitanv.listener;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.listener.PrefetchListener
 * @description 用于手动确认模式的消息监听类，需实现ChannelAwareMessageListener接口
 * @date 2021/4/22 19:24
 */
@Component
public class PrefetchListener implements ChannelAwareMessageListener {

    private static Logger logger = LoggerFactory.getLogger(PrefetchListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String consumerQueue = message.getMessageProperties().getConsumerQueue();
        try {
            String msg = new String(message.getBody());
            if ("prefetch-test-queue".equals(consumerQueue)) {
                logger.info(Thread.currentThread().getName() + "接收的消息：" + msg);
            }
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            logger.error("消息处理失败");
            channel.basicNack(deliveryTag, false, false);
        }
    }
}