package com.example.upload.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_upload")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "filename")
	private String fileName;

	@Column(name = "type")
	private String type;

	@Column(name = "doc")
	private byte[] doc;
}
