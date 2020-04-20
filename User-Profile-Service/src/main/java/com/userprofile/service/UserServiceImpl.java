package com.userprofile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.userprofile.dto.UserDetailCreateRequest;
import com.userprofile.dto.UserDetailResponse;
import com.userprofile.dto.UserDetailUpdateRequest;
import com.userprofile.exceptions.UserNotFoundException;
import com.userprofile.model.UserDetail;
import com.userprofile.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository repository;

	@PersistenceContext
	private EntityManager entityManager;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<UserDetailResponse> findAllUsers() {
		logger.info("Service: fetch all user details");
		List<UserDetail> userDetails = repository.findAll();
		if (userDetails == null || userDetails.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		return userDetails.stream().map(UserDetailResponse::of).collect(Collectors.toList());
	}

	@Override
	public UserDetailResponse findUserById(Long id) throws UserNotFoundException {
		logger.info("Service: fetch all user details by id : " + id);
		Optional<UserDetail> userDetail = repository.findById(id);

		if (userDetail == null || !userDetail.isPresent()) {
			throw new UserNotFoundException("No User found for id = " + id);
		}
		return UserDetailResponse.of(userDetail.get());
	}

	@Override
	public UserDetailResponse saveUser(UserDetailCreateRequest userDTO) {
		logger.info("Service: create new user detail");
		return UserDetailResponse.of(repository.save(UserDetail.of(userDTO)));
	}

	@Override
	public void deleteById(Long id) {
		logger.info("Service: delete user detail by id : " + id);
		repository.deleteById(id);
	}

	@Override
	public UserDetailResponse updateUser(UserDetailUpdateRequest updatedUser, Long id) {
		logger.info("Service: Update user detail for id : " + id);
		Optional<UserDetail> userOpt = repository.findById(id);

		if (userOpt.isPresent() && userOpt.get() != null) {
			if (updatedUser.getAge() != null) {
				userOpt.get().setAge(updatedUser.getAge());
			}
			if (updatedUser.getAddress() != null) {
				userOpt.get().setAddress(updatedUser.getAddress());
			}
			if (updatedUser.getMobileNumber() != null) {
				userOpt.get().setMobileNumber(updatedUser.getMobileNumber());
			}
			return UserDetailResponse.of(repository.save(userOpt.get()));
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	@Override
	public List<UserDetailResponse> findUserByFilter(UserDetailCreateRequest userDTO) {
		logger.info("Service: fetch all user details based on provided filter");
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetail> query = criteria.createQuery(UserDetail.class);
		Root<UserDetail> root = query.from(UserDetail.class);
		List<Predicate> predicate = new ArrayList<>();

		if (userDTO.getFirstName() != null && !userDTO.getFirstName().isEmpty()) {
			predicate.add(criteria.equal(root.get("firstName"), userDTO.getFirstName()));
		}
		if (userDTO.getLastName() != null && !userDTO.getLastName().isEmpty()) {
			predicate.add(criteria.equal(root.get("lastName"), userDTO.getLastName()));
		}
		if (userDTO.getAge() != null) {
			predicate.add(criteria.equal(root.get("age"), userDTO.getAge()));
		}
		if (userDTO.getAddress() != null && !userDTO.getAddress().isEmpty()) {
			predicate.add(criteria.equal(root.get("address"), userDTO.getAddress()));
		}
		if (userDTO.getMobileNumber() != null) {
			predicate.add(criteria.equal(root.get("mobileNumber"), userDTO.getMobileNumber()));
		}
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.select(root);
		TypedQuery<UserDetail> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList().stream().map(UserDetailResponse::of).collect(Collectors.toList());
	}
}
