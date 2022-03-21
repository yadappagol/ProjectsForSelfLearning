package com.spring.multipart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.multipart.dto.FileDto;
import com.spring.multipart.model.FileOperations;
import com.spring.multipart.response.ResponseMessage;
import com.spring.multipart.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "/api/v1/admin", tags = "Admin Resource")
public class FileResource {

	@Autowired
	private FileService service;

	/**
	 * URL : http://localhost:8085/api/v1/upload
	 */
	@ApiOperation(value = "All Doctors", notes = "All Doctors", tags = "Book My Doctor Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All data fetched"),
			@ApiResponse(code = 404, message = "NOt Found"), @ApiResponse(code = 403, message = "Access Denied") })
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> upload(@RequestParam("files") MultipartFile[] multipartFiles) {
		List<FileOperations> uploadFile = service.uploadFile(multipartFiles);
		if (uploadFile != null) {
			return new ResponseEntity<>(new ResponseMessage("success", "Files Uploaded Successfully", uploadFile),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage("failed", "File Not Uploaded"), HttpStatus.NOT_FOUND);
	}

	/**
	 * URL : http://localhost:8085/api/v1/download/{id}
	 */
	@ApiOperation(value = "All Doctors", notes = "All Doctors", tags = "Book My Doctor Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All data fetched"),
			@ApiResponse(code = 404, message = "NOt Found"), @ApiResponse(code = 403, message = "Access Denied") })
	@GetMapping("/download/{id}")
	public ResponseEntity<?> download(@PathVariable int id) {
		FileDto downloadFile = service.downloadFileById(id);
		if (downloadFile != null) {
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(downloadFile.getFileType()))
					.body(downloadFile.getResource());
		}
		return new ResponseEntity<>(new ResponseMessage("failed", "File Not Downloaded"), HttpStatus.NOT_FOUND);
	}

	/**
	 * URL : http://localhost:8085/api/v1/delete/{id}
	 */
	@ApiOperation(value = "All Doctors", notes = "All Doctors", tags = "Book My Doctor Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All data fetched"),
			@ApiResponse(code = 404, message = "NOt Found"), @ApiResponse(code = 403, message = "Access Denied") })
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseMessage> delete(@PathVariable int id) {
		String delete = service.deleteById(id);
		if (delete.equalsIgnoreCase("success"))
			return new ResponseEntity<>(new ResponseMessage("success", "File Deleted Successfully"), HttpStatus.OK);
		else
			return new ResponseEntity<>(new ResponseMessage("failed", "File Not Deleted"), HttpStatus.NOT_FOUND);
	}

}
