package com.example.upload.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.upload.domain.UploadDomain;
import com.example.upload.dto.DocDownload;
import com.example.upload.dto.ResponseDawnloadDoc;
import com.example.upload.repository.UploadRepository;
import com.example.upload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	UploadRepository uploadRepository;
	HashSet<Long> set = null;

	@Override
	public String singleUpload(MultipartFile[] file) {
		List<UploadDomain> upload = new ArrayList<>();

		Arrays.stream(file).forEach((obj) -> {
			try {
				upload.add(UploadDomain.builder().fileName(obj.getOriginalFilename()).type(obj.getContentType())
						.doc(obj.getBytes()).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		List<UploadDomain> uploadDomain = uploadRepository.saveAll(upload);
		if (uploadDomain != null) {
			for (UploadDomain n : uploadDomain) {
				System.out.println(n.getFileName());
			}
			return "Document is Uploaded Sucessfully : ";
		}

		return null;
	}

	@Override
	public ResponseDawnloadDoc getUploadedDoc(Long[] ids) {
		List<DocDownload> data = null;
		ResponseDawnloadDoc responce = new ResponseDawnloadDoc();
		set = new HashSet<>();
		try {
			Iterable<Long> iterable = Arrays.asList(ids);
			for (Long t : ids) {
				set.add(t);
			}
			data = uploadRepository.findAllById(iterable).stream().map(this::mapToDoc).collect(Collectors.toList());
			Iterator<Long> itr = set.iterator();
			if (set.size() != 0) {
				String key = "";
				while (itr.hasNext()) {
					key = key + itr.next() + ",";
				}
				key = key.substring(0, key.length() - 1);
				HashMap<String, String> hMap = new HashMap<>();
				hMap.put("status", "These are the invalid id please inter correct id");
				hMap.put("ids", key);
				responce.setError(hMap);
			}
			responce.setDoc(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	public DocDownload mapToDoc(UploadDomain upload) {
		DocDownload doc = new DocDownload();
		doc.setId(upload.getId());
		set.remove(upload.getId());
		doc.setFileName(upload.getFileName());
		doc.setDoc(Base64.getEncoder().encodeToString(upload.getDoc()));
		return doc;

	}
}
