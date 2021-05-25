package br.com.compasso.projetothree.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import br.com.compasso.projetothree.config.MessageConfig;
import br.com.compasso.projetothree.dto.ProductStatus;

@Component
public class Consumer {
    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(ProductStatus productStatus) {
        System.out.println("Message received from queue: \n" + productStatus);
    }
}
