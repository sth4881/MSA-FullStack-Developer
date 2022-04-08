package com.kosa.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.dto.AttachDTO;
import com.kosa.app.service.ArticleService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
@Controller
@RequestMapping("board/{page}")
public class ArticleController {
	@Value("${articlePerPage}")
	private int articlePerPage; // 한 페이지에 보여지는 게시물의 개수
	
	@Value("${blockPerPage}")
	private int blockPerPage; // 한 페이지에 보여지는 페이지 블록의 개수
	
	@Value("${uploadFolder}")
	private String uploadFolder; // 파일 업로드 경로
	
	// 생성자 주입
	private ArticleService service;
	public ArticleController(ArticleService service) {
		this.service = service;
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", "/");
	}
	
	// 특정 파일이 이미지 타입인지 검사하는 메소드
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	@GetMapping("insert")
	public String insertArticle() {
		return "article.insert";
	}
	@PostMapping("insert")
	@ResponseBody
	public String insertArticle(
		@RequestParam String title, @RequestParam String author,
		@RequestParam String password, @RequestParam String content,
		@PathVariable long page, MultipartFile[] attach, Model model) {
		try {
			model.addAttribute("page", page);
			
			ArticleDTO articleDTO = new ArticleDTO();
			articleDTO.setTitle(title); articleDTO.setAuthor(author);
			articleDTO.setPassword(password); articleDTO.setContent(content);
			service.insertArticle(articleDTO);
			
			String uploadFolderPath = getFolder();
			File uploadPath = new File(uploadFolder, uploadFolderPath);
			if(uploadPath.exists()==false) {
				uploadPath.mkdirs();
			}
			
			//List<AttachDTO> list = new ArrayList<>();
			for(MultipartFile file : attach) {
				log.info("------------------------------------------------");
				log.info("Original File Name : " + file.getOriginalFilename());
				log.info("Original File Size : " + file.getSize());
				
				String uploadFileName = file.getOriginalFilename();
				//uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
				log.info("Upload File Name : " + uploadFileName);
				try {
					//File saveFile = new File(uploadFolder, uploadFileName);
					File saveFile = new File(uploadPath, uploadFileName);
					file.transferTo(saveFile);
					
					AttachDTO attachDTO = new AttachDTO();
					attachDTO.setAno(articleDTO.getAno());
					attachDTO.setFname(uploadFileName);
					attachDTO.setFpath(uploadFolderPath);
					if(checkImageType(saveFile)) {
						attachDTO.setFtype(1);
						
						FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
						Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 100, 100);
						thumbnail.close();
					}
					//list.add(attachDTO);
					service.insertAttach(attachDTO);
				} catch (Exception e) {
					log.info(e.getMessage());
					return "2";
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			return "2";
		}
		return "1";
	}
}
