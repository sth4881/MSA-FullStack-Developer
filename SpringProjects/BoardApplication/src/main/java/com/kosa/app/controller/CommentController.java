package com.kosa.app.controller;

import org.springframework.stereotype.Controller;

import com.kosa.app.service.CommentService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommentController {
	// 생성자 주입
	private CommentService service;
	public CommentController(CommentService service) {
		this.service = service;
	}
}