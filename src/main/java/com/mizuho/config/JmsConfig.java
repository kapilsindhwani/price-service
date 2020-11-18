package com.mizuho.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;


@Configuration
public class JmsConfig {

    @Bean(name = "queueConnectionFactory")
    @Primary
    public ConnectionFactory queueConnectionFactory() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        return connectionFactory;
    }

    @Bean(name = "topicConnectionFactory")
    public ConnectionFactory topicConnectionFactory() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        return connectionFactory;
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("test-queue");
    }

    @Bean(name = "jmsQueueTemplate")
    @Autowired
    public JmsTemplate jmsTemplate(@Qualifier(value = "queueConnectionFactory") ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean(name = "jmsTopicTemplate")
    @Autowired
    public JmsTemplate jmsTopicTemplate(@Qualifier(value = "topicConnectionFactory") ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

}
