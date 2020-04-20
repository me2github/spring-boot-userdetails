package com.userprofile.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.userprofile.dto.UserDetailUpdateJMSRequest;
import com.userprofile.dto.UserDetailUpdateRequest;
import com.userprofile.service.UserService;

@Component
@EnableJms
public class JmsConsumer {
	private final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

	@Autowired
	private UserService service;

	@JmsListener(destination = "user-queue", containerFactory = "myFactory")
	public void updateUser(UserDetailUpdateJMSRequest updatedUser) {
		logger.info("Message received {} ", updatedUser);
		service.updateUser(UserDetailUpdateRequest.of(updatedUser), updatedUser.getId());
		logger.info("Update done");
	}

	@JmsListener(destination = "user-queue", containerFactory = "myFactory")
	public void deleteUser(Long id) {
		logger.info("Message received for delete {} ", id);
		service.deleteById(id);
		logger.info("Delete done");
	}
}
