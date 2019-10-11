package com.github.mall.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * create by: HPC
 * description: mq配置
 * create time: 2019/5/16
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

//    @Autowired
//    private MqSendMessageService mqSendMessageService;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/mall");
        /*是否启动发送到交换机回调*/
        connectionFactory.setPublisherConfirms(true);
        /*returnCallback 未投递到queue退回模式*/
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    /**
     *
     * 必须是prototype类型RabbitTemplate只允许设置一个callback方法,所以使用原始模式
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setReturnCallback(this::returnCallback);
        template.setConfirmCallback(this::confirmCallback);
        template.setMandatory(true);
        return template;
    }

    /**
     * 到达交换机成功回调
     */
    private void confirmCallback(CorrelationData correlationData, boolean ack, String s) {
        long id = Long.parseLong(correlationData.getId());
        /*成功到达交换机*/
        if (ack) {
            /*到达成功更新消息发送状态*/
//            mqSendMessageService.updateMsgToSuccess(id);
        } else {
            /*到达交换机失败*/
//            mqSendMessageService.updateMsgToFail(id);
        }
    }

    //到达目的队列失败 TODO
    private void returnCallback(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
//        log.info("not to queue  router key:" + routingKey + " exchange:" + exchange + " replyText:" + replyText + " message:" + (message != null ? message.getBody() : " replyCode:" + replyCode));

    }

}