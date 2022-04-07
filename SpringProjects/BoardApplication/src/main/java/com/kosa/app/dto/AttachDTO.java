package com.kosa.app.dto;

import lombok.Data;

@Data
public class AttachDTO {
	private long fno; // 파일 번호
	private String fname; // 파일 이름
	private boolean image; // 이미지인지 아닌지 판별
	private long ano; // 게시글 번호
}