package com.example.upload.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.upload.dto.DocumentDawnload;
import com.example.upload.dto.ResponseDawnloadDoc;
import com.example.upload.service.UploadService;
import com.example.upload.validation.GlobalValidator;

@RestController
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	GlobalValidator validator;

	@Autowired
	UploadService service;

	@GetMapping()
	public ResponseEntity<?> uploadhSingleDocument() {
		try {
			System.err.println("hello fromm");
			HashMap<String, String> test = new HashMap<>();
			test.put("error", "this is test");
			return new ResponseEntity<Object>(test, HttpStatus.SERVICE_UNAVAILABLE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/uploadDoc")
	public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile[] document) {
		try {
			String result = null;
			HashMap<String, String> regError = validator.validateDocument(document);
			if (regError != null) {
				return new ResponseEntity<Object>(regError, HttpStatus.OK);
			} else {
				result = service.singleUpload(document);
				if (result == null) {
					HashMap<String, String> test = new HashMap<>();
					test.put("Error", "Something went wrong");
					return new ResponseEntity<>(test, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@PostMapping("/getDoc")
	public ResponseEntity<?> getDocument(@RequestBody(required = true) DocumentDawnload id) {
		ResponseDawnloadDoc doc = service.getUploadedDoc(id.getDocId());
		return new ResponseEntity<>(doc, HttpStatus.OK);
	}

}