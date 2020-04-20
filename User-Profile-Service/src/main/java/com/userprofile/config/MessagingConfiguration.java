package com.userprofile.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.userprofile.dto.UserDetailUpdateJMSRequest;

@Configuration
public class MessagingConfiguration {
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    
    private static final String ORDER_QUEUE = "user-queue";
 
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("com.auth"));
        return connectionFactory;
    }
     
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setPubSubDomain(true);
        template.setDefaultDestinationName(ORDER_QUEUE);
        return template;
    }
    
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

		Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
		typeIdMappings.put("JMS_TYPE", UserDetailUpdateJMSRequest.class);

		converter.setTypeIdMappings(typeIdMappings);
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
