package net.developia.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;
import net.developia.spring03.service.BoardService;

@Log4j
@Controller
@RequestMapping("board/{page}/{no}") // URL에 board/페이지 번호를 붙여서 접근하도록 한다.
public class BoardDetailController {
	private BoardService boardService;

	// 생성자 주입은 @Autowired를 써주지 않아도 된다.
	public BoardDetailController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/")
	public String detail(
		@PathVariable long page,
		@PathVariable long no,
		@RequestParam long virtual,
		Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetails(no);
			model.addAttribute("boardDTO", boardDTO);
			model.addAttribute("virtual", virtual);
			return "board.detail";
		} catch (RuntimeException e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "../../");
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", "detail 출력 에러");
			model.addAttribute("url", "../../");
			return "result";
		}
	}
	
	@GetMapping("delete")
	public String delete(@PathVariable long no, Model model) {
		return "board.delete";
	}
	
	@PostMapping("delete")
	public ModelAndView delete(
			@ModelAttribute BoardDTO boardDTO,
			@RequestParam long virtual, // virtual number,
			Model model) {
		// 삭제가 성공하든 실패하든 result.jsp로 이동
		ModelAndView mav = new ModelAndView("result");
		try {
			boardService.deleteBoard(boardDTO);
			mav.addObject("msg", boardDTO.getNo() + "번 게시물이 삭제되었습니다.");
			mav.addObject("virtual", virtual);
			mav.addObject("url", "../../1/");
		} catch (Exception e) {
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}
	
	// 글 상세보기 페이지(detail.jsp) 내용 불러오기
	@GetMapping(value="update")
	public String update(@PathVariable long no, Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetails(no);
			model.addAttribute("boardDTO", boardDTO);
			return "board.update";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물이 존재하지 않습니다.");
			model.addAttribute("url", "../../1/");
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
			model.addAttribute("url", "./");
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
		}
		return "result";
	}
}