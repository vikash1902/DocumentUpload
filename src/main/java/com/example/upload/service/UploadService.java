package com.example.upload.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.upload.dto.ResponseDawnloadDoc;

public interface UploadService {

	public String singleUpload(MultipartFile[] file) throws IOException;

	public ResponseDawnloadDoc getUploadedDoc(Long[] ids);
}
