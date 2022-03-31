package net.developia.spring02.mvc1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*") // URL 요청시 spring02/test/memberList2
public class MvcController10 {
	@RequestMapping("/memberList2")
	public ResponseEntity<List<MemoDTO>> listMembers2() {
		List<MemoDTO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			MemoDTO dto = new MemoDTO();
			dto.setName("정대만" + i);
			dto.setPassword("1234" + i);
			dto.setTitle("그래 난 정대만" + i);
			dto.setContent("포기를 모르는 남자지" + i);
			list.add(dto);
		}
		return new ResponseEntity<List<MemoDTO>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/res3")
	public ResponseEntity<List<MemoDTO>> res() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text-html; charset=utf-8");
		List<MemoDTO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			MemoDTO dto = new MemoDTO();
			dto.setName("정대만" + i);
			dto.setPassword("1234" + i);
			dto.setTitle("그래 난 정대만" + i);
			dto.setContent("포기를 모르는 남자지" + i);
			list.add(dto);
		}
		return new ResponseEntity<List<MemoDTO>>(list, responseHeaders, HttpStatus.CREATED);
	}
}
