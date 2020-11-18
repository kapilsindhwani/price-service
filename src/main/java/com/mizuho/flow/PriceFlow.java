package com.mizuho.flow;

import com.mizuho.config.ChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
public class PriceFlow {

    @Bean
    @Autowired
    public IntegrationFlow storePrice(@Qualifier(ChannelConfig.PRICE_CHANNEL) MessageChannel priceChannel, @Qualifier(ChannelConfig.DOWNSTREAM_CHANNEL) MessageChannel downstreamChannel){
        return IntegrationFlows.from(priceChannel)
                .handle("priceDao", "insert")
                .channel(ChannelConfig.DOWNSTREAM_CHANNEL)
                .get();
    }
}
