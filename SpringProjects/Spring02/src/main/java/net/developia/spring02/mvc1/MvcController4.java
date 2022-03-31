package net.developia.spring02.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MvcController4 {
	@GetMapping("/param")
	public String param() {
		return "param";
	}
	
	// form으로부터 전송받은 데이터의 이름이 myname과 같으면 바로 값을 넣어준다.
	// form으로부터 값이 넘어오지 않으면 defaultValue를 설정하여 기본값을 넣어준다.
	// 기본값을 설정하지 않더라도 RequestParam을 적어주는 것이 좋다.
	@PostMapping("/param")
	public String param(@RequestParam(defaultValue="손흥민") String myname, int age, Model model) {
		log.info("name : " + myname);
		log.info("age " + age);
		String iam = "그래 난 " + myname + ". " + age + "세지.";
		model.addAttribute("iam", iam);
		return "param_result";
	}
}
