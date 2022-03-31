package net.developia.spring02.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController3 {
	// Http Method를 정해주지 않으면 모든 Http Method를 받음
	// 매개변수가 2개 이상이면 변수를 모두 적어줘야함
	@RequestMapping(value="/iam", method= {
			RequestMethod.POST,
			RequestMethod.GET})
	public String iam(ModelMap model) {
		model.addAttribute("iam", "그래, 난 정대만");
		return "iam";
	}
	
	@GetMapping("/iam2")
	public String iam2(Model model) {
		model.addAttribute("iam", "난, 그래 정대만");
		return "iam";
	}
}