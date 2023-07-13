package com.petsimx.takoyomai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsimx.takoyomai.model.UserInformationDetail;

public interface UserInformationDetailsRepository extends JpaRepository<UserInformationDetail, Integer> {

	Optional<UserInformationDetail> findByEmail(String username);

}
