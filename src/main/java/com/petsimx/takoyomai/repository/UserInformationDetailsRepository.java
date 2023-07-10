package com.petsimx.takoyomai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsimx.takoyomai.model.UserInformationDetails;

public interface UserInformationDetailsRepository extends JpaRepository<UserInformationDetails, Integer> {

	Optional<UserInformationDetails> findByEmail(String username);

}
