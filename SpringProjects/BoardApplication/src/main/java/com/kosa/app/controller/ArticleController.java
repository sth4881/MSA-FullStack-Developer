package com.kosa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.service.AppService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("board/{page}")
public class ArticleController {
	@Value("${articlePerPage}")
	private int articlePerPage; // 한 페이지에 보여지는 게시물의 개수
	
	@Value("${blockPerPage}")
	private int blockPerPage; // 한 페이지에 보여지는 페이지 블록의 개수
	
	// 생성자 주입
	private AppService service;
	public ArticleController(AppService service) {
		this.service = service;
	}
	
	// 게시글 목록 불러오기
	@GetMapping("/")
	public String getArticleList(@PathVariable("page") long page, Model model) {
		try {
			// 전체 게시물의 개수
			long articleCount = service.getArticleCount();
			// 전체 페이지 개수 = 전체 게시물 개수 / 한 페이지에 보여지는 게시물의 개수
			long pageCount = articleCount / articlePerPage;
			// 예를 들어, 게시물의 개수가 101개인 경우, 11페이지 필요하므로 총 페이지의 개수를 증가시켜준다.
			if(articleCount % articlePerPage != 0) pageCount++;
			
			// 시작 페이지 = (현재 페이지-1) / 페이지 블록 크기 * 페이지 블록 크기 + 1
			long startPage = (page-1) / blockPerPage * blockPerPage + 1;
			// 마지막 페이지 = (현재 페이지-1) / 페이지 블록 크기 * 페이지 블록 크기 + 페이지 블록 크기
			long endPage = (page-1) / blockPerPage * blockPerPage + blockPerPage;
			// 마지막 페이지 개수가 전체 페이지 개수보다 많은 경우, 마지막 페이지를 전체 페이지 개수로 맞춰준다.
			if(endPage > pageCount) endPage = pageCount;
			
			List<ArticleDTO> list = service.getArticleList(page);
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("articleCount", articleCount);
			model.addAttribute("articlePerPage", articlePerPage);
			return "article.list";
		} catch (Exception e) {
			log.info(e.getMessage());
			model.addAttribute("msg", "Error : getArticleList()");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
	
	// 게시글 등록하기
	@GetMapping("/insert")
	public String insertArticle() {
		return "article.insert";
	}
	@PostMapping("/insert")
	public String insertArticle(@ModelAttribute ArticleDTO dto, Model model) {
		log.info(dto.toString());
		try {
			service.insertArticle(dto);
			return "redirect:../1/";
		} catch (Exception e) {
			model.addAttribute("msg", "Error : insertArticle()");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
}
