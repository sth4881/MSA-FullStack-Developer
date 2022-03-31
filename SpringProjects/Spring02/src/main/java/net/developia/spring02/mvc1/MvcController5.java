package net.developia.spring02.mvc1;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MvcController5 {
	@GetMapping("/scissors")
	public void scissors() {
		
	}
	@PostMapping("/scissors")
	public String scissors(int you, Model model) {
		Random rand = new Random();
		int com = rand.nextInt(3)+1;
		
		log.info("you : " + you);
		log.info("com : " + com);

		String result = "";
		if(you==com) result = "무승부";
		else if(you==1 && com==2 || you==2 && com==3 || you==3 && com==1) result="당신의 패배";
		else result="당신의 승리";
		
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "가위"); map.put(2, "바위"); map.put(3, "보");
		model.addAttribute("you", map.get(you)); // you obhect를 1이면 가위, 2면 바위, 3이면 보
		model.addAttribute("com", map.get(com)); // com object를 1이면 가위, 2면 바위, 3이면 보
		model.addAttribute("result", result); // 당신의 승리 or 당신의 패배 or 무승부입니다.
		return "scissors_result";
	}
}
