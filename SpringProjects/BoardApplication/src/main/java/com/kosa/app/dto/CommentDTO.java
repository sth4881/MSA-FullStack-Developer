package com.kosa.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
	private long cno;
	private String content;
	private String author;
	private String password;
	private Date createdAt;
	private Date updatedAt;
}
