package com.mizuho.gateway;

import com.mizuho.config.ChannelConfig;
import com.mizuho.model.Price;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(errorChannel = "errorChannel", defaultRequestChannel = ChannelConfig.INBOUND_CHANNEL)
public interface PriceGateway {

    @Gateway
    void storePrice(Message payload);

}
