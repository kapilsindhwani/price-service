package com.mizuho.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfig {

    public static final String PRICE_CHANNEL = "PRICE_CHANNEL";
    public static final String INBOUND_CHANNEL = "INBOUND_CHANNEL";
    public static final String DOWNSTREAM_CHANNEL = "DOWNSTREAM_CHANNEL";
    @Bean(name = PRICE_CHANNEL)
    public MessageChannel priceChannel(){
        return MessageChannels.direct().get();
    }

    @Bean(name = INBOUND_CHANNEL)
    public MessageChannel inboundChannel(){
        return MessageChannels.direct().get();
    }

    @Bean(name = DOWNSTREAM_CHANNEL)
    public MessageChannel downstreamChannel(){
        return MessageChannels.direct().get();
    }
}
