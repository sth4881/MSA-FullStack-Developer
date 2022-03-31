package net.developia.spring01.di04;

import org.springframework.stereotype.Component;

@Component // OrangeSpeaker를 Spring Bean에 등록한다.
public class OrangeSpeaker implements Speaker {
	@Override
	public void volumeUp() {
		System.out.println("OrangeSpeaker : 소리를 키웁니다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("OrangeSpekaer : 소리를 줄입니다.");
	}
}
