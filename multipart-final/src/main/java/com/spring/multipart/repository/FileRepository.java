package com.spring.multipart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.multipart.model.FileOperations;

@Repository
public interface FileRepository extends JpaRepository<FileOperations, Integer> {

	public FileOperations findByFileId(int id);

}
