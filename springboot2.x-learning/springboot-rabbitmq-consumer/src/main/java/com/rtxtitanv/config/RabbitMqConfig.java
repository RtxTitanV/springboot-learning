package com.rtxtitanv.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.RabbitMqConfig
 * @description 配置交换机和队列
 * @date 2021/4/17 16:48
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 简单模式，声明队列
     *
     * @return Queue对象
     */
    @Bean(name = "simpleQueue")
    public Queue simpleQueue() {
        /**
         * 参数明细
         * 1.name：队列名称
         * 2.durable：是否持久化队列，true表示持久化，false表示不持久化
         * 3.exclusive：是否独占此队列，true表示是，false表示否
         * 4.autoDelete：队列不用是否自动删除，true表示是，false表示否
         */
        return new Queue("simple-queue", true, false, false);
    }

    /**
     * 工作队列模式，声明队列
     *
     * @return Queue对象
     */
    @Bean(name = "workQueue")
    public Queue workQueue() {
        return new Queue("work-queue", true, false, false);
    }

    /**
     * 发布订阅模式，声明交换机
     *
     * @return FanoutExchange对象
     */
    @Bean(name = "publishExchange")
    public FanoutExchange publishExchange() {
        //创建一个fanout类型的交换机
        return new FanoutExchange("publish-exchange");
    }

    /**
     * 发布订阅模式，声明队列1
     *
     * @return Queue对象
     */
    @Bean(name = "publishQueue1")
    public Queue publishQueue1() {
        return new Queue("publish-queue1", true, false, false);
    }

    /**
     * 发布订阅模式，声明队列2
     *
     * @return Queue对象
     */
    @Bean(name = "publishQueue2")
    public Queue publishQueue2() {
        return new Queue("publish-queue2", true, false, false);
    }

    /**
     * 将队列publish-queue1与交换机publish-exchange绑定
     *
     * @param queue          队列
     * @param fanoutExchange fanout类型交换机
     * @return Binding对象
     */
    @Bean(name = "bindingQueue1ToFanoutExchange")
    public Binding bindingQueue1ToFanoutExchange(@Qualifier("publishQueue1") Queue queue,
        FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /**
     * 将队列publish-queue2与交换机publish-exchange绑定
     *
     * @param queue          队列
     * @param fanoutExchange fanout类型交换机
     * @return Binding对象
     */
    @Bean(name = "bindingQueue2ToFanoutExchange")
    public Binding bindingQueue2ToFanoutExchange(@Qualifier("publishQueue2") Queue queue,
        FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

//    /**
//     * 路由模式，声明交换机
//     *
//     * @return DirectExchange对象
//     */
//    @Bean(name = "routingExchange")
//    public DirectExchange routingExchange() {
//        //创建一个direct类型的交换机
//        return new DirectExchange("routing-exchange");
//    }
//
//    /**
//     * 路由模式，声明队列1
//     *
//     * @return Queue对象
//     */
//    @Bean(name = "routingQueue1")
//    public Queue routingQueue1() {
//        return new Queue("routing-queue1", true, false, false);
//    }
//
//    /**
//     * 路由模式，声明队列2
//     *
//     * @return Queue对象
//     */
//    @Bean(name = "routingQueue2")
//    public Queue routingQueue2() {
//        return new Queue("routing-queue2", true, false, false);
//    }
//
//    /**
//     * 路由模式，声明队列3
//     *
//     * @return Queue对象
//     */
//    @Bean(name = "routingQueue3")
//    public Queue routingQueue3() {
//        return new Queue("routing-queue3", true, false, false);
//    }
//
//    /**
//     * 将队列routing-queue1与交换机routing-exchange绑定，绑定键为info
//     *
//     * @param queue          队列
//     * @param directExchange direct类型交换机
//     * @return Binding对象
//     */
//    @Bean(name = "bindingQueue1ToDirectExchangeWithInfo")
//    public Binding bindingQueue1ToDirectExchangeWithInfo(@Qualifier("routingQueue1") Queue queue,
//        DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("info");
//    }
//
//    /**
//     * 将队列routing-queue2与交换机routing-exchange绑定，绑定键为info
//     *
//     * @param queue          队列
//     * @param directExchange direct类型交换机
//     * @return Binding对象
//     */
//    @Bean(name = "bindingQueue2ToDirectExchangeWithInfo")
//    public Binding bindingQueue2ToDirectExchangeWithInfo(@Qualifier("routingQueue2") Queue queue,
//        DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("info");
//    }
//
//    /**
//     * 将队列routing-queue1与交换机routing-exchange绑定，绑定键为warning
//     *
//     * @param queue          队列
//     * @param directExchange direct类型交换机
//     * @return Binding对象
//     */
//    @Bean("bindingQueue1ToDirectExchangeWithWarning")
//    public Binding bindingQueue1ToDirectExchangeWithWarning(@Qualifier("routingQueue1") Queue queue,
//        DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("warning");
//    }
//
//    /**
//     * 将队列routing-queue3与交换机routing-exchange绑定，绑定键为warning
//     *
//     * @param queue          队列
//     * @param directExchange direct类型交换机
//     * @return Binding对象
//     */
//    @Bean("bindingQueue3ToDirectExchangeWithWarning")
//    public Binding bindingQueue3ToDirectExchangeWithWarning(@Qualifier("routingQueue3") Queue queue,
//        DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("warning");
//    }
//
//    /**
//     * 将队列routing-queue2与交换机routing-exchange绑定，绑定键为error
//     *
//     * @param queue          队列
//     * @param directExchange direct类型交换机
//     * @return Binding对象
//     */
//    @Bean(name = "bindingQueue2ToDirectExchangeWithError")
//    public Binding bindingQueue2ToDirectExchangeWithError(@Qualifier("routingQueue2") Queue queue,
//        DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("error");
//    }
//
//    /**
//     * 将队列routing-queue3与交换机routing-exchange绑定，绑定键为error
//     *
//     * @param queue          队列
//     * @param directExchange direct类型交换机
//     * @return Binding对象
//     */
//    @Bean(name = "bindingQueue3ToDirectExchangeWithError")
//    public Binding bindingQueue3ToDirectExchangeWithError(@Qualifier("routingQueue3") Queue queue,
//        DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("error");
//    }

    /**
     * 主题模式，声明交换机
     *
     * @return DirectExchange对象
     */
    @Bean(name = "topicsExchange")
    public TopicExchange topicsExchange() {
        //创建一个direct类型的交换机
        return new TopicExchange("topics-exchange");
    }

    /**
     * 主题模式，声明队列1
     *
     * @return Queue对象
     */
    @Bean(name = "topicsQueue1")
    public Queue topicsQueue1() {
        return new Queue("topics-queue1", true, false, false);
    }

    /**
     * 主题模式，声明队列2
     *
     * @return Queue对象
     */
    @Bean(name = "topicsQueue2")
    public Queue topicsQueue2() {
        return new Queue("topics-queue2", true, false, false);
    }

    /**
     * 主题模式，声明队列3
     *
     * @return Queue对象
     */
    @Bean(name = "topicsQueue3")
    public Queue topicsQueue3() {
        return new Queue("topics-queue3", true, false, false);
    }

    /**
     * 将队列topics-queue1与交换机topics-exchange绑定，绑定键为*.rabbitmq.*
     *
     * @param queue         队列
     * @param topicExchange topic类型交换机
     * @return Binding对象
     */
    @Bean(name = "bindingQueue1ToTopicExchange")
    public Binding bindingQueue1ToTopicExchange(@Qualifier("topicsQueue1") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("*.rabbitmq.*");
    }

    /**
     * 将队列topics-queue2与交换机topics-exchange绑定，绑定键为*.*.server
     *
     * @param queue         队列
     * @param topicExchange topic类型交换机
     * @return Binding对象
     */
    @Bean(name = "bindingQueue2ToTopicExchange")
    public Binding bindingQueue2ToTopicExchange(@Qualifier("topicsQueue2") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("*.*.server");
    }

    /**
     * 将队列topics-queue3与交换机topics-exchange绑定，绑定键为com.#
     *
     * @param queue         队列
     * @param topicExchange topic类型交换机
     * @return Binding对象
     */
    @Bean(name = "bindingQueue3ToTopicExchange")
    public Binding bindingQueue3ToTopicExchange(@Qualifier("topicsQueue3") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("com.#");
    }

    @Bean(name = "confirmTestExchange")
    public DirectExchange confirmTestExchange() {
        //创建一个direct类型的交换机
        return new DirectExchange("confirm-test-exchange");
    }

    @Bean(name = "ackTestQueue1")
    public Queue ackTestQueue1() {
        return new Queue("ack-test-queue1", true, false, false);
    }

    @Bean(name = "ackTestQueue2")
    public Queue ackTestQueue2() {
        return new Queue("ack-test-queue2", true, false, false);
    }

    @Bean(name = "concurrencyTestQueue")
    public Queue concurrencyTestQueue() {
        return new Queue("concurrency-test-queue", true, false, false);
    }

    @Bean(name = "prefetchTestQueue")
    public Queue prefetchTestQueue() {
        return new Queue("prefetch-test-queue", true, false, false);
    }
}