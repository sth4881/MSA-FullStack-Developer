package net.developia.spring02.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MvcController6 {
	// @PathVariable을 통해서 전달받은 변수는 값이 알아서 전달된다.
	@GetMapping("/want/{want}")
	public String want(@PathVariable("want") String want, Model model) {
		model.addAttribute("iwant", want + "가 하고 싶어요~");
		return "want";
	}
	
	@GetMapping("/want/{want}/{teacher}")
	public String want(
		@PathVariable("want") String want,
		@PathVariable("teacher") String teacher,
		Model model) {
		model.addAttribute("want", teacher +"님," + want + "가 하고 싶어요~");
		return "want";
	}

	@GetMapping("/want/hello")
	public String hello(){
		return "hello";
	}	
}