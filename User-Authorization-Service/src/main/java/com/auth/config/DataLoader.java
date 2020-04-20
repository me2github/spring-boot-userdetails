package com.auth.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.auth.model.User;
import com.auth.repo.UserAuthRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final UserAuthRepository repository;

	public DataLoader(UserAuthRepository repository) {
		this.repository = repository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		User user1 = User.of("admin", "admin", "ADMIN");
		repository.save(user1);
	}

}
