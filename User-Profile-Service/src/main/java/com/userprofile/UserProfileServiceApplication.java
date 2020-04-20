package com.userprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfileServiceApplication.class, args);
	}

	/*
	 * @Bean public JmsListenerContainerFactory<?> myFactory( ConnectionFactory
	 * connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
	 * DefaultJmsListenerContainerFactory factory = new
	 * DefaultJmsListenerContainerFactory(); configurer.configure(factory,
	 * connectionFactory); //factory.setConcurrency("1-1"); return factory; }
	 * 
	 * @Bean public MessageConverter jacksonJmsMessageConverter() {
	 * MappingJackson2MessageConverter converter = new
	 * MappingJackson2MessageConverter();
	 * 
	 * Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
	 * typeIdMappings.put("JMS_TYPE", UserDetailUpdateJMSRequest.class);
	 * 
	 * converter.setTypeIdMappings(typeIdMappings);
	 * converter.setTargetType(MessageType.TEXT);
	 * converter.setTypeIdPropertyName("_type"); return converter; }
	 */
    

}
