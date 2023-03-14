package com.example.upload.validation;

import java.util.HashMap;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class GlobalValidator {

	public HashMap<String, String> validateDocument(MultipartFile[] file) {
		HashMap<String, String> error = new HashMap<>();

		for (MultipartFile m : file) {
			String result = validateDocumentSize((int) m.getSize() / 1024, 10, 300);
			if (!result.equals("true")) {
				error.put(m.getOriginalFilename() + "_size", result);

			}
			result = checkFileExtension(m.getOriginalFilename());
			if (!result.equals("pdf")) {
				error.put(m.getOriginalFilename() + "_extension", "Please select Pdf file only. ");

			}
		}
		return error.size() == 0 ? null : error;
	}

	public String validateDocumentSize(int actualSize, int minSize, int maxSize) {
		if (actualSize >= maxSize) {
			return "Document size exceeds 300kb. Please upload file Smaller than 300KB ";
		}
		if (actualSize <= minSize) {
			return "Document size is greater than 10KB ";
		}

		return "true";
	}

	public String checkFileExtension(String filename) {
		String ext = "";
		if (filename.contains(".")) {
			ext = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		}

		return ext;
	}
}
