package com.mizuho.flow;

import com.mizuho.config.ChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

/**
 * Class to handle the price
 */
@Configuration
public class PriceFlow {

    /**
     * This Flow will store the price in object store and pass the information to the downstream channel
     * @param priceChannel Inbound channel which will receive Price payload
     * @param downstreamChannel Channel which will receive the message to be distributed to the downstream systems
     * @return IntegrationFlow
     */
    @Bean
    @Autowired
    public IntegrationFlow storePrice(@Qualifier(ChannelConfig.PRICE_CHANNEL) MessageChannel priceChannel, @Qualifier(ChannelConfig.DOWNSTREAM_CHANNEL) MessageChannel downstreamChannel){
        return IntegrationFlows.from(priceChannel)
                .handle("priceDao", "insert")
                .channel(ChannelConfig.DOWNSTREAM_CHANNEL)
                .get();
    }
}
