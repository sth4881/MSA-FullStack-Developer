package com.kosa.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.service.AppService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("board/{page}/{ano}")
public class DetailController {
	// 생성자 주입
	private AppService service;
	public DetailController(AppService service) {
		this.service = service;
	}
	
	// 게시글 상세보기
	@GetMapping("/")
	public String detail(
		@PathVariable long page, // 페이지 번호
		@PathVariable long ano, // 게시물 번호
		@RequestParam long vno, // 가상 번호(list.jsp에서 query-string을 통해 들어옴)
		Model model) {
		try {
			ArticleDTO dto = service.getArticleDetail(ano);
			model.addAttribute("page", page);
			model.addAttribute("dto", dto);
			model.addAttribute("vno", vno); // detail.jsp로 값을 보내주기 위해서 사용
			return "article.detail";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "../../");
			return "result";
		}
	}
	
	// 게시글 수정하기
	@GetMapping(value="update")
	public String update(@PathVariable long ano, Model model) {
		try {
			ArticleDTO dto = service.getArticleDetail(ano);
			model.addAttribute("dto", dto);
			return "article.update";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물이 존재하지 않습니다.");
			model.addAttribute("url", "../../1/");
			return "result";
		}
	}
	@PostMapping(value="update")
	public String update(@ModelAttribute ArticleDTO dto, Model model) {
		log.info(dto.toString());
		try {
			service.updateArticle(dto);
			model.addAttribute("msg", dto.getAno()+ "번 게시물이 수정되었습니다.");
			model.addAttribute("url", "./");
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
		}
		return "result";
	}
	
	// 게시글 삭제하기
	@GetMapping("delete")
	public String delete(@PathVariable long ano, Model model) {
		return "article.delete";
	}
	@PostMapping("delete")
	public String delete(
		@ModelAttribute ArticleDTO dto,
		Model model) {
		try {
			service.deleteArticle(dto);
			model.addAttribute("msg", dto.getAno() + "번 게시물이 삭제되었습니다.");
			model.addAttribute("url", "../../1/");
			return "article.delete";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
}