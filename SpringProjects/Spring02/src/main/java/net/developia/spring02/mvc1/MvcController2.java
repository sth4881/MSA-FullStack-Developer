package net.developia.spring02.mvc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MvcController2 {
	// Model에 값을 축라할 때는 addObjectg
	@RequestMapping("hello2")
	public ModelAndView hello2() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello2");
		mav.addObject("msg1", "안선생님~");
		mav.addObject("msg2", "농구가 하고싶어요ㅜㅜ");
		return mav;
	}
	
	@RequestMapping("hello3")
	public ModelAndView hello3() {
		return new ModelAndView("hello2", "msg1", "안선생님~");
	}
	
	// ModelMap : 키-값의 쌍으로 매개변수를 넘길 수 있음
	// ModelMap에 값을 추가할 때는 addAttribute
	@RequestMapping("hello4")
	public String hello4(ModelMap model) {
		model.addAttribute("msg1", "강백호씨");
		model.addAttribute("msg2", "농구 좋아하세요~?");
		return "hello2";
	}
}