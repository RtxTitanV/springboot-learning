package com.rtxtitanv.listener;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.listener.RabbitMqListener
 * @description 用于监听队列
 * @date 2021/4/17 17:47
 */
@Component
public class RabbitMqListener {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "simple-queue")
    public void simpleQueue(String msg) {
        logger.info("监听simple-queue队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(queues = "work-queue")
    public void workQueue1(String msg) {
        logger.info("监听work-queue队列的消费者1接收到的消息：" + msg);
    }

    @RabbitListener(queues = "work-queue")
    public void workQueue2(String msg) {
        logger.info("监听work-queue队列的消费者2接收到的消息：" + msg);
    }

    @RabbitListener(queues = "publish-queue1")
    public void publishQueue1(String msg) {
        logger.info("监听publish-queue1队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(queues = "publish-queue2")
    public void publishQueue2(String msg) {
        logger.info("监听publish-queue2队列的消费者接收到的消息：" + msg);
    }

//    @RabbitListener(queues = "routing-queue1")
//    public void routingQueue1(String msg) {
//        logger.info("监听routing-queue1队列的消费者接收到的消息：" + msg);
//    }
//
//    @RabbitListener(queues = "routing-queue2")
//    public void routingQueue2(String msg) {
//        logger.info("监听routing-queue2队列的消费者接收到的消息：" + msg);
//    }
//
//    @RabbitListener(queues = "routing-queue3")
//    public void routingQueue3(String msg) {
//        logger.info("监听routing-queue3队列的消费者接收到的消息：" + msg);
//    }

    @RabbitListener(bindings = {@QueueBinding(
        value = @Queue(value = "routing-queue1", durable = "true", exclusive = "false", autoDelete = "false"),
        exchange = @Exchange(value = "routing-exchange", type = "direct"), key = {"info", "warning"})})
    public void routingQueue1(String msg) {
        logger.info("监听routing-queue1队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(bindings = {@QueueBinding(
        value = @Queue(value = "routing-queue2", durable = "true", exclusive = "false", autoDelete = "false"),
        exchange = @Exchange(value = "routing-exchange", type = "direct"), key = {"info", "error"})})
    public void routingQueue2(String msg) {
        logger.info("监听routing-queue2队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(bindings = {@QueueBinding(
        value = @Queue(value = "routing-queue3", durable = "true", exclusive = "false", autoDelete = "false"),
        exchange = @Exchange(value = "routing-exchange", type = "direct"), key = {"warning", "error"})})
    public void routingQueue3(String msg) {
        logger.info("监听routing-queue3队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(queues = "topics-queue1")
    public void topicsQueue1(String msg) {
        logger.info("监听topics-queue1队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(queues = "topics-queue2")
    public void topicsQueue2(String msg) {
        logger.info("监听topics-queue2队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(queues = "topics-queue3")
    public void topicsQueue3(String msg) {
        logger.info("监听topics-queue3队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(bindings = {@QueueBinding(
        value = @Queue(value = "confirm-queue", durable = "true", exclusive = "false", autoDelete = "false"),
        exchange = @Exchange(value = "confirm-exchange", type = ExchangeTypes.DIRECT), key = {"info"})})
    public void producerConfirmQueue(String msg) {
        logger.info("监听confirm-queue队列的消费者接收到的消息：" + msg);
    }

    @RabbitListener(bindings = {
        @QueueBinding(value = @Queue(value = "ack-queue", durable = "true", exclusive = "false", autoDelete = "false"),
            exchange = @Exchange(value = "ack-exchange", type = ExchangeTypes.DIRECT), key = {"info"})})
    public void consumerAckQueue(Integer msg, Channel channel, Message message) throws IOException {
        try {
            logger.info("监听ack-queue队列的消费者接收到的消息：" + msg);
            logger.info("消息处理开始");
            int i = 10 / msg;
            logger.info("消息处理成功");
            logger.info("10除msg等于" + i);
            // 肯定确认，确认消息已经成功消费，可以从队列删除
            // 参数1：当前消息的唯一id
            // 参数2：是否批量确认，true表示一次确认所有小于deliveryTag的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("消息处理失败");
            // 否定确认，确认消息没有成功消费
            // 参数1：当前消息的唯一id
            // 参数2：是否批量确认，true表示一次确认所有小于deliveryTag的消息
            // 参数3：消息是否重新入队，true表示将确认为没有成功消费的消息重新放入队列，是否重新入队可以根据具体业务逻辑进行设置
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

//    /**
//     * 监听concurrency-test-queue队列
//     * 通过@RabbitListener的queuesToDeclare属性声明一个队列，绑定默认交换机，路由键为队列名
//     * concurrency min-max设置并发消费者数，min为并发消费者最小数量，max为并发消费者最大数量
//     *
//     * @param msg 消息
//     */
//    @RabbitListener(queuesToDeclare = @Queue("concurrency-test-queue"), concurrency = "5-10")
//    public void concurrencyConsumerQueue(String msg) {
//        logger.info(Thread.currentThread().getName() + "接收的消息：" + msg);
//    }
//
//    /**
//     * 监听prefetch-test-queue队列
//     * containerFactory 指定自定义监听容器工厂
//     *
//     * @param msg 消息
//     */
//    @RabbitListener(queuesToDeclare = @Queue("prefetch-test-queue"), concurrency = "5-10",
//        containerFactory = "simpleRabbitListenerContainerFactory")
//    public void concurrencyConsumerQueue(String msg) {
//        logger.info(Thread.currentThread().getName() + "接收的消息：" + msg);
//    }
}