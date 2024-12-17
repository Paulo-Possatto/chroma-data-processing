package com.chromamon.analysis.config;

import com.chromamon.analysis.constants.PatternConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue diagnosticResultsQueue() {
        return new Queue("diagnostic-results", true);
    }

    @Bean
    public Queue serviceAQueue() {
        return new Queue("processor-queue");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(PatternConstants.EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingServiceA(FanoutExchange fanoutExchange, Queue serviceAQueue) {
        return BindingBuilder.bind(serviceAQueue).to(fanoutExchange);
    }


}
