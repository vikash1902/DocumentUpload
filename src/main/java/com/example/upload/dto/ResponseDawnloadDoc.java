package com.example.upload.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ResponseDawnloadDoc {

	
	private HashMap<String, String> error;
	private List<DocDownload> doc;
}
