package net.developia.spring03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;
import net.developia.spring03.service.BoardService;

@Log4j
// 생성자 주입은 @Autowired를 써주지 않아도 된다.
@Controller
public class BoardController {
	private BoardService boardService;

	@Value("${pageSize}")
	private int pageSize; // 한 페이지에 보여지는 게시물의 개수
	
	@Value("${blockSize}")
	private int blockSize; // 페이지 하단에 보여지는 페이지 블록의 개수
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 글 등록하기
	@GetMapping(value="insert")
	public String insertBoard() {
		return "insert";
	}
	
	@PostMapping(value="insert")
	public String insertBoard(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		try {
			boardService.insertBoard(boardDTO);
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg", "입력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
	
	// 글 목록 불러오기
//	@GetMapping("list")
//	public String list(Model model) throws Exception {
//		try {
//			List<BoardDTO> list = boardService.getBoardList();
//			model.addAttribute("list", list);
//			return "list";
//		} catch(Exception e) {
//			model.addAttribute("msg", "list 출력 에러");
//			model.addAttribute("url", "javascript:history.back();");
//			return "result";
//		}
//	}
	
	// 페이징을 통한 글 목록 불러오기
	// 값이 넘어오면 그 값을 사용하되, 넘어오지 않으면 1페이지를 사용
	@GetMapping("list")
	public String list(@RequestParam(defaultValue="1") long page, Model model) {
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
			return "list";
		} catch(Exception e) {
			model.addAttribute("msg", "list 출력 에러");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
	
	// 글 상세정보 불러오기
	@GetMapping("detail")
	public String detail(
			@RequestParam(defaultValue="-1") long no,
			@RequestParam(defaultValue="-1") long page, Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetails(no);
			model.addAttribute("boardDTO", boardDTO);
			model.addAttribute("page", page);
			return "detail";
		} catch (RuntimeException e) {
			model.addAttribute("msg", "detail 출력 에러");
			model.addAttribute("url", "list");
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", "detail 출력 에러");
			model.addAttribute("url", "list");
			return "result";
		}
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam long no, Model model) {
		model.addAttribute(no);
		return "delete";
	}
	
	@PostMapping("delete")
	public ModelAndView delete(@ModelAttribute BoardDTO boardDTO, Model model) {
		// 삭제가 성공하든 실패하든 result.jsp로 이동
		ModelAndView mav = new ModelAndView("result");
		try {
			boardService.deleteBoard(boardDTO);
			mav.addObject("msg", boardDTO.getNo() + "번 게시물이 삭제되었습니다.");
			mav.addObject("url", "list");
		} catch (Exception e) {
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}
	
	// 글 상세보기 페이지(detail.jsp) 내용 불러오기
	@GetMapping("update")
	public String update(@RequestParam long no, Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetails(no);
			model.addAttribute("boardDTO", boardDTO);
			return "update";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물이 존재하지 않습니다.");
			model.addAttribute("url", "list");
			return "result";
		}
	}
	
	// 글 삭제 페이지(delete.jsp)와 같이 내용
	@PostMapping(value="update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		try {
			boardService.updateBoard(boardDTO);
			model.addAttribute("msg", boardDTO.getNo()+ "번 게시물이 수정되었습니다.");
			model.addAttribute("url", "detail?no=" + boardDTO.getNo());
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
		}
		return "result";
	}
}