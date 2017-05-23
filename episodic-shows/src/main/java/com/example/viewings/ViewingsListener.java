package com.example.viewings;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Created by trainer20 on 5/22/17.
 */
@Configuration
public class ViewingsListener implements RabbitListenerConfigurer {


    private final EventProcessingService eventProcessingService;

    public ViewingsListener(EventProcessingService eventProcessingService) {
        this.eventProcessingService = eventProcessingService;
    }


    @RabbitListener(queues = "episodic-progress")
    public void receiveMessage(final EventMessage message){

        try{
            System.out.println("************************************************");
            System.out.print(message.toString());
            System.out.println("************************************************");
            eventProcessingService.upsertViewing(message);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
