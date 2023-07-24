package com.petsimx.takoyomai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsimx.takoyomai.model.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

}
