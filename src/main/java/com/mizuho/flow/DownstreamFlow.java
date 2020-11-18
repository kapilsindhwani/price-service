package com.mizuho.flow;

import com.mizuho.config.ChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;

import javax.jms.ConnectionFactory;

@Configuration
public class DownstreamFlow {

    @Bean
    @Autowired
    public IntegrationFlow sendToDownstream(@Qualifier(ChannelConfig.DOWNSTREAM_CHANNEL) MessageChannel downstreamChannel, @Qualifier(value = "jmsQueueTemplate") JmsTemplate jmsTemplate){
        return IntegrationFlows.from(downstreamChannel)
                .transform(new ObjectToJsonTransformer())
                .routeToRecipients(recipientListRouterSpec -> recipientListRouterSpec
                .recipientFlow(flow -> flow.handle(Jms.outboundAdapter(jmsTemplate).destination("downstream1-queue")))
                .recipientFlow(flow -> flow.handle(Jms.outboundAdapter(jmsTemplate).destination("downstream2-queue"))))
                .get();
    }
}
