package com.rtxtitanv;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.RabbitMqTest
 * @description 用于生产者发送消息的测试类
 * @date 2021/4/17 17:02
 */
// SpringBoot2.4.0开始使用Junit5，不需要加@Runwith
@SpringBootTest(classes = RabbitMqProducerApplication.class)
class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static Logger logger = LoggerFactory.getLogger(RabbitMqTest.class);

    /**
     * 简单模式，发送消息测试
     */
    @Test
    void simpleSendTest() {
        /*
         * 发送消息
         * 参数明细
         * 1.exchange：交换机名称，如果没有指定，则使用Default Exchange
         * 由于没指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但是不能显
         * 示绑定或解除绑定
         * 2.routingKey：路由键，由于使用默认交换机，指定路由键为队列名
         * 3.object：发送的消息
         */
        rabbitTemplate.convertAndSend("", "simple-queue", "Hello World");
    }

    /**
     * 工作队列模式，发送消息测试
     */
    @Test
    void workSendTest() {
        for (int i = 1; i <= 10; i++) {
            rabbitTemplate.convertAndSend("", "work-queue", "message" + i);
        }
    }

    /**
     * 发布订阅模式，发送消息测试
     */
    @Test
    void publishSendTest() {
        rabbitTemplate.convertAndSend("publish-exchange", "", "Hello World");
    }

    /**
     * 路由模式，发送消息测试
     */
    @Test
    void routingSendTest() {
        rabbitTemplate.convertAndSend("routing-exchange", "info", "日志信息");
        rabbitTemplate.convertAndSend("routing-exchange", "warning", "警告信息");
        rabbitTemplate.convertAndSend("routing-exchange", "error", "错误信息");
    }

    /**
     * 主题模式，发送消息测试
     */
    @Test
    void topicsSendTest() {
        rabbitTemplate.convertAndSend("topics-exchange", "www.rabbitmq.server", "message1");
        rabbitTemplate.convertAndSend("topics-exchange", "com.rabbitmq.client", "message2");
        rabbitTemplate.convertAndSend("topics-exchange", "com.kafka.server", "message3");
    }

    /**
     * 测试发送方确认机制
     *
     * @throws InterruptedException
     */
    @Test
    void producerConfirm() throws InterruptedException {
        // 开启Mandatory，才能触发回调函数，如果设置为false，则关闭Mandatory，消息直接丢弃
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            logger.info("ConfirmCallback---相关数据：" + correlationData);
            logger.info("ConfirmCallback---确认情况：" + ack);
            logger.info("ConfirmCallback---原因：" + cause);
        });

        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            // 消息对象
            Message message = returnedMessage.getMessage();
            // 回应码
            int replyCode = returnedMessage.getReplyCode();
            // 回应信息
            String replyText = returnedMessage.getReplyText();
            // 交换机
            String exchange = returnedMessage.getExchange();
            // 路由键
            String routingKey = returnedMessage.getRoutingKey();

            logger.info("ReturnsCallback---消息对象：" + message);
            logger.info("ReturnsCallback---回应码：" + replyCode);
            logger.info("ReturnsCallback---回应信息：" + replyText);
            logger.info("ReturnsCallback---交换机：" + exchange);
            logger.info("ReturnsCallback---路由键：" + routingKey);
        });

//        rabbitTemplate.convertAndSend("confirm-exchange1", "info", "message");
//        rabbitTemplate.convertAndSend("confirm-test-exchange", "info", "message");
        rabbitTemplate.convertAndSend("confirm-exchange", "info", "message");
        // 为了便于测试，发送消息之后让线程休眠一会，避免测试方法结束之后因资源关闭导致confirmCallback出错
        Thread.sleep(2000);
    }

    /**
     * 测试接收方确认机制
     *
     * @throws InterruptedException
     */
    @Test
    void consumerAck() throws InterruptedException {
        // 开启Mandatory，才能触发回调函数，如果设置为false，则关闭Mandatory，消息直接丢弃
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.info("消息发送到交换机成功");
            } else {
                logger.error("消息发送到交换机失败");
                logger.error("失败原因：" + cause);
            }
        });

        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            logger.error("消息从交换机到队列失败");
        });
//        rabbitTemplate.convertAndSend("ack-exchange", "info", 2);
        rabbitTemplate.convertAndSend("ack-exchange", "info", 0);
        // 为了便于测试，发送消息之后让线程休眠一会，避免测试方法结束之后因资源关闭导致confirmCallback出错
        Thread.sleep(2000);
    }

    /**
     * 测试接收方确认机制
     */
    @Test
    void consumerAck2() {
        rabbitTemplate.convertAndSend("", "ack-test-queue1", "Hello");
        rabbitTemplate.convertAndSend("", "ack-test-queue2", "");
    }

    /**
     * 测试并发消费
     */
    @Test
    void concurrencyConsumer() {
        for (int i = 1; i <= 10; i++) {
            rabbitTemplate.convertAndSend("", "concurrency-test-queue", "message" + i);
        }
    }
}