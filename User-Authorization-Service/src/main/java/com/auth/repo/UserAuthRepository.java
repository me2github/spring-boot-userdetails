package com.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.model.User;

@Repository
public interface UserAuthRepository extends JpaRepository<User, Long> {

}
