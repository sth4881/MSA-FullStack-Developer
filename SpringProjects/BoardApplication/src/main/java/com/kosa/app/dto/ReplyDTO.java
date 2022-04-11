package com.kosa.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {
	private long cno;
	private String reply;
	private String author;
	private String password;
	private Date createdAt;
	private Date updatedAt;
	private long ano;
}