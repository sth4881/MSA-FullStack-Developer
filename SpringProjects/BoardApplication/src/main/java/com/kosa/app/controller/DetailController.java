package com.kosa.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.dto.AttachDTO;
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
		@RequestParam long vno, // 가상 번호(list.jsp에서 query-string으로 들어옴)
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
	
	// 특정 게시물의 첨부파일 목록을 모두 가져와서 전송
//	@GetMapping(value="/display", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ResponseEntity<List<AttachDTO>> getAttachList(long ano) {
//		return new ResponseEntity<>(service.getAttachList(ano), HttpStatus.OK);
//	}
	
	// 게시글 수정하기(GET)
	//@GetMapping("update")
	@RequestMapping(value="/{vno}/update", method=RequestMethod.GET)
	public String update(
		@PathVariable long ano, 
		@PathVariable long vno,
		Model model) {
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
	
	// 게시글 수정하기(POST)
	//@PostMapping("update")
	@RequestMapping(value="/{vno}/update", method=RequestMethod.POST)
	public String update(
		@ModelAttribute ArticleDTO dto,
		@PathVariable long vno,
		Model model) {
		log.info(dto.toString());
		try {
			service.updateArticle(dto);
			model.addAttribute("msg", vno + "번 게시물이 수정되었습니다.");
			model.addAttribute("url", "../?vno=" + vno);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
		}
		return "result";
	}
	
	// 게시글 삭제하기(GET)
	//@GetMapping("delete")
	@RequestMapping(value="/{vno}/delete", method=RequestMethod.GET)
	public String delete(
		@PathVariable long ano,
		@PathVariable long vno,
		Model model) {
		model.addAttribute(vno);
		return "article.delete";
	}
	
	// 게시글 삭제하기(POST)
	//@PostMapping("delete")
	@RequestMapping(value="/{vno}/delete", method=RequestMethod.POST)
	public String delete(
		@ModelAttribute ArticleDTO dto,
		@PathVariable long vno,
		Model model) {
		try {
			log.info("삭제 성공");
			service.deleteArticle(dto);
			model.addAttribute("msg", vno + "번 게시물이 삭제되었습니다.");
			model.addAttribute("url", "../../../1/");
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
		}
		return "result";
	}
}
