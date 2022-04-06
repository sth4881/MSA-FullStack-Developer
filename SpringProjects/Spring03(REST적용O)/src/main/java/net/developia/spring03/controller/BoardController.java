package net.developia.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;
import net.developia.spring03.service.BoardService;

@Log4j
@Controller
@RequestMapping("board/{page}") // URL에 board/페이지 번호를 붙여서 접근하도록 한다.
public class BoardController {
	private BoardService boardService;

	@Value("${pageSize}")
	private int pageSize; // 한 페이지에 보여지는 게시물의 개수
	
	@Value("${blockSize}")
	private int blockSize; // 페이지 하단에 보여지는 페이지 블록의 개수
	
	// 생성자 주입은 @Autowired를 써주지 않아도 된다.
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 글 등록하기
	@GetMapping(value="insert")
	public String insertBoard() {
		return "board.insert";
	}
	
	// RESTful을 통한 글 등록하기
	@PostMapping(value="insert")
	public String insertBoard(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		try {
			boardService.insertBoard(boardDTO);
			return "redirect:../1/";
		} catch (Exception e) {
			model.addAttribute("msg", "입력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
	
	// RESTful 방식의 글 목록 불러오기
	@GetMapping("/")
	public String list(@PathVariable("page") long page, Model model) {
		try {
			long recordCount = boardService.getBoardCount(); // 전체 게시물의 개수
			long pageCount = recordCount / pageSize; // 총 페이지 개수 = 전체 게시물 개수 / 한 페이지에 보여지는 게시물의 개수
			if(recordCount % pageSize != 0) pageCount++; // 예를 들어, 게시물의 개수가 101개인 경우, 11페이지 필요하므로 총 페이지의 개수를 증가시켜준다.
			
			// 시작 페이지 & 마지막 페이지 계산 방법
			long startPage = (page - 1) / blockSize * blockSize + 1;
			long endPage = (page - 1) / blockSize * blockSize + blockSize;
			if(endPage > pageCount) endPage = pageCount; // 마지막 페이지 값이 총 페이지 값보다 큰 경우, 마지막 페이지를 총 페이지 값으로 맞춰준다.
			
			List<BoardDTO> list = boardService.getBoardListWithPaging(page);
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("recordCount", recordCount);
			model.addAttribute("pageSize", pageSize);
			return "board.list";
		} catch(Exception e) {
			model.addAttribute("msg", "list 출력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
}