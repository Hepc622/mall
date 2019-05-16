package com.github.mall.rabbit.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * create by: HPC
 * description: 消费监听
 * create time: 2019/5/16
 */
@Component
@Slf4j
public class SalesListener {
//    @Autowired
//    private CommercialSalesRecordClient commercialSalesRecordClient;
//
//    @RabbitListener(queues = QueueConstants.QUEUE_COMMERCIAL_SALES)
//    @RabbitHandler
//    public ResultVo process(@Payload MqSendMessage sendMessage, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InvocationTargetException, IllegalAccessException {
//        //将发送实体转换为消费实体
//        MqConsumerMessage consumerMessage = new MqConsumerMessage();
//        BeanUtils.copyProperties(consumerMessage, sendMessage);
//        return this.commercialSalesRecordClient.addCommercialSales(consumerMessage);
//    }
}