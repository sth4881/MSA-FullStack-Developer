package net.developia.spring02.mvc1;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MvcController7 {
	@GetMapping("/form")
	public void form() {}
	
	// @ModelAttribute를 붙여주지 않아도 되지만 기본적으로 붙여준다.
	// MemoDTO와 같이 클래스 단위로 받아온 Command Object는 그냥 넘겨주면 된다.
	// 하지만 param1은 'Model model'을 매개변수로 주지 않으면 jsp로 값이 넘어가지 않는다.
	@PostMapping("/form")
	public String form(
		@Valid @ModelAttribute MemoDTO memoDTO,		
		BindingResult br,
		Model model) {
		
		if(br.hasErrors()) {
			return "form";
		} else {
			log.info(memoDTO.toString());
			//model.addAttribute("param1", param1);
			return "form_result";
		}
	}
	
	@GetMapping("/jquerytest")
	public String jqueryTest() {
		return "jquerytest";
	}
}
