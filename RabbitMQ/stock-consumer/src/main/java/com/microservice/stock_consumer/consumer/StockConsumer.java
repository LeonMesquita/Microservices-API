
package com.microservice.stock_consumer.consumer;

import com.microservice.stock_consumer.constants.RabbitMQConstants;
import com.microservice.stock_consumer.dtos.StockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {
    @RabbitListener(queues = RabbitMQConstants.QUEUE_STOCK)
    private void consumer(StockDTO stockDTO) {
        System.out.println(stockDTO.getProductCode());
        System.out.println(stockDTO.getQuantity());
        System.out.println("----------------------------------------------");
    }
}
