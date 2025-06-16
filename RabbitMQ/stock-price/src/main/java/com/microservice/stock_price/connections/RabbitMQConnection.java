package com.microservice.stock_price.connections;

import com.microservice.stock_price.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relationship(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    // essa annotation executa a função assim que a classe for construida
    @PostConstruct
    private void add() {
         Queue queueStock = this.queue(RabbitMQConstants.QUEUE_STOCK);
         Queue queuePrice = this.queue(RabbitMQConstants.QUEUE_PRICE);

         DirectExchange directExchange = this.directExchange();

         Binding stockBinding = this.relationship(queueStock, directExchange);
         Binding priceBinding = this.relationship(queuePrice, directExchange);

         this.amqpAdmin.declareQueue(queueStock);
         this.amqpAdmin.declareQueue(queuePrice);

         this.amqpAdmin.declareExchange(directExchange);

         this.amqpAdmin.declareBinding(stockBinding);
         this.amqpAdmin.declareBinding(priceBinding);
    }
}
