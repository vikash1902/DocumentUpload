package com.example.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.upload.domain.UploadDomain;

@EnableJpaRepositories
public interface UploadRepository extends JpaRepository<UploadDomain, Long> {

}
