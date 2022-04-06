package net.developia.spring04.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();                                                                                                                         
		}
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}
}
