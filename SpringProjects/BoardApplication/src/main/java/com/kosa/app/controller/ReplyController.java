package com.kosa.app.controller;

import org.springframework.stereotype.Controller;

import com.kosa.app.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ReplyController {
	// 생성자 주입
	private ReplyService service;
	public ReplyController(ReplyService service) {
		this.service = service;
	}
}