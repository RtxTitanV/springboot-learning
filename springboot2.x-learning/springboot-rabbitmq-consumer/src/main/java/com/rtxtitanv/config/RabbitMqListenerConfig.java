package com.rtxtitanv.config;

import com.rtxtitanv.listener.AckListener;
import com.rtxtitanv.listener.ConcurrencyListener;
import com.rtxtitanv.listener.PrefetchListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.RabbitMqListenerConfig
 * @description 配置自定义监听容器和自定义监听容器工厂
 * @date 2021/4/21 15:41
 */
@Configuration
public class RabbitMqListenerConfig {
    @Resource
    private CachingConnectionFactory cachingConnectionFactory;
    @Resource
    private AckListener ackListener;
    @Resource
    private ConcurrencyListener concurrencyListener;
    @Resource
    private PrefetchListener prefetchListener;

    /**
     * 自定义监听容器
     *
     * @return SimpleMessageListenerContainer
     */
    @Bean(name = "simpleMessageListenerContainer1")
    public SimpleMessageListenerContainer simpleMessageListenerContainer1() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        // 设置接收方确认模式，AcknowledgeMode.MANUAL为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置监听队列，多个队列可以用,号隔开，队列必须已经创建
        container.setQueueNames("ack-test-queue1", "ack-test-queue2");
        // 设置消息监听类
        container.setMessageListener(ackListener);
        return container;
    }

    /**
     * 自定义监听容器
     *
     * @return SimpleMessageListenerContainer
     */
    @Bean(name = "simpleMessageListenerContainer2")
    public SimpleMessageListenerContainer simpleMessageListenerContainer2() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        // 设置并发消费者最小数量
        container.setConcurrentConsumers(5);
        // 设置并发消费者最大数量
        container.setMaxConcurrentConsumers(10);
        // 设置接收方确认模式，AcknowledgeMode.MANUAL为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置监听队列，多个队列可以用,号隔开，队列必须已经创建
        container.setQueueNames("concurrency-test-queue");
        // 设置消息监听类
        container.setMessageListener(concurrencyListener);
        return container;
    }

    /**
     * 自定义监听容器
     *
     * @return SimpleMessageListenerContainer
     */
    @Bean(name = "simpleMessageListenerContainer3")
    public SimpleMessageListenerContainer simpleMessageListenerContainer3() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        // 设置并发消费者最小数量
        container.setConcurrentConsumers(5);
        // 设置并发消费者最大数量
        container.setMaxConcurrentConsumers(10);
        // 设置每个consumer在单位时间内接收的消息数
        container.setPrefetchCount(20);
        // 设置接收方确认模式，AcknowledgeMode.MANUAL为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置监听队列，多个队列可以用,号隔开，队列必须已经创建
        container.setQueueNames("prefetch-test-queue");
        // 设置消息监听类
        container.setMessageListener(prefetchListener);
        return container;
    }

    /**
     * 自定义监听容器工厂
     *
     * @return SimpleRabbitListenerContainerFactory
     */
    @Bean(name = "simpleRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        // 设置每个consumer在单位时间内接收的消息数
        factory.setPrefetchCount(10);
        return factory;
    }
}