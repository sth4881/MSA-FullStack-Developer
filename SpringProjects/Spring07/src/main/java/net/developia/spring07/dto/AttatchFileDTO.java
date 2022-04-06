package net.developia.spring07.dto;

import lombok.Data;

@Data
public class AttatchFileDTO {
	private String fileName;
	private String uploadPath;
	private boolean image;
}
