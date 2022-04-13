package com.kosa.app.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.dto.AttachDTO;
import com.kosa.app.service.ArticleService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("board/{page}/{ano}")
public class DetailController {
	@Value("${uploadPath}")
	private String uploadPath; // 파일 업로드 경로
	
	// 생성자 주입
	private ArticleService service;
	public DetailController(ArticleService service) {
		this.service = service;
	}
	
	// 게시글에 포함된 파일들을 모두 삭제하는 메소드
	public void deleteAttachFiles(List<AttachDTO> list) {
		if(list == null || list.size() == 0) return;
		log.info(list);
		list.forEach(attach -> {
			try {
				// 원본 파일 삭제
				Path source = Paths.get(uploadPath + attach.getFpath()
				+ "\\" + attach.getUuid() + "_" + attach.getFname());
				if(Files.deleteIfExists(source)) log.info("원본 삭제 성공");
				
				// 썸네일 삭제
				if(Files.probeContentType(source).startsWith("image")) {
					Path thumbnail = Paths.get(uploadPath + attach.getFpath()
					+ "\\s_" + attach.getUuid() + "_" + attach.getFname());
					if(Files.deleteIfExists(thumbnail)) log.info("썸네일 삭제 성공");
				}
			} catch (Exception e) {
				log.error("File Deletion Error : " + e.getMessage());
			}
		});
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
			model.addAttribute("articleDTO", dto);
			model.addAttribute("page", page);
			model.addAttribute("vno", vno); // detail.jsp로 값을 보내주기 위해서 사용
			
			List<AttachDTO> list = service.getAttachList(ano);
			for(AttachDTO item : list) {
//				item.setFpath(encodeURIComponent(item.getFpath()));
				log.info(item);
			}
			model.addAttribute("attachList", list);
			
			return "article.detail";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "../../");
			return "result";
		}
	}
	
	// 첨부파일 데이터 전송
	@GetMapping("display")
	@ResponseBody
	public ResponseEntity<byte[]> getAttachFile(String fname) { // 브라우저에 보내주는 파일 종류에 따라 MIME 타입이 달라진다.
		// 업로드 파일 경로 + 파일명으로 파일 생성
		File file = new File(uploadPath + fname);
		try {
			HttpHeaders header = new HttpHeaders();
			// 적절한 MIME 타입 데이터를 Http의 헤더 메시지에 포함되도록 처리한다.
			header.add("Content-Type", Files.probeContentType(file.toPath())); 
			return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 첨부파일 다운로드
	@GetMapping(value="download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fname) {
		Resource resource = new FileSystemResource(uploadPath + fname);
		if(!resource.exists()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String resourceName = resource.getFilename();
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		HttpHeaders headers = new HttpHeaders();
		log.info("Resource : " + resource);
		log.info("Resource Name : " + resourceName);
		log.info("Resource Original Name : " + resourceOriginalName);
		try {
			// 파일 이름이 한글인 경우 저장할 때 깨지는 문제를 막기 위해서
			// 다운로드 시 저장되는 이름은 'Content-Disposition'을 이용해서 지정한다.
			String downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			headers.add("Content-Disposition", "attachment;filename=" + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
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
			if(service.updateArticle(dto)) log.info("수정 성공");
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
		@PathVariable long ano,
		@PathVariable long vno,
		Model model) {
		try {
			List<AttachDTO> list = service.getAttachList(ano);
			if(service.deleteArticle(dto)) {
				deleteAttachFiles(list);
				log.info("삭제 성공");
			}
			model.addAttribute("msg", vno + "번 게시물이 삭제됐습니다.");
			model.addAttribute("url", "../../../1/");
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
		}
		return "result";
	}
}
