package net.developia.spring02.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController9 {
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String test() {
		return "info";
	}
	
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void print(@RequestBody MemoDTO dto) {
		System.out.println(dto);
	}
}
