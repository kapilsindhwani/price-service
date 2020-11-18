package com.mizuho.flow;

import com.mizuho.config.ChannelConfig;
import com.mizuho.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.messaging.MessageChannel;

import javax.jms.ConnectionFactory;

@Configuration
public class GatewayFlow {

    @Bean
    @Autowired
    public IntegrationFlow vendorSpecificInboundJmsFlow(@Qualifier(value = "queueConnectionFactory")ConnectionFactory connectionFactory){
        return IntegrationFlows
                .from(Jms.messageDrivenChannelAdapter(connectionFactory)
                        .destination("vendor1-queue"))
                .channel(ChannelConfig.PRICE_CHANNEL)
                .get();
    }

    @Bean
    @Autowired
    public IntegrationFlow inboundJsonJmsFlow(@Qualifier(value = "queueConnectionFactory")ConnectionFactory connectionFactory) {
        return IntegrationFlows
                .from(Jms.messageDrivenChannelAdapter(connectionFactory)
                        .destination("inbound-json-queue"))
                .transform(new JsonToObjectTransformer(Price.class))
                .channel(ChannelConfig.PRICE_CHANNEL)
                .get();
    }

    @Bean
    @Autowired
    public IntegrationFlow httpFlow(@Qualifier(ChannelConfig.INBOUND_CHANNEL) MessageChannel inboundChannel){
        return IntegrationFlows.from(inboundChannel)
                .channel(ChannelConfig.PRICE_CHANNEL)
                .get();
    }


}
