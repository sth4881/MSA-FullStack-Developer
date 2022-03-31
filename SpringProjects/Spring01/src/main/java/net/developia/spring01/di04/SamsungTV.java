package net.developia.spring01.di04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// SamsungTV를 Spring Bean에 등록하고, value를 'tv'로 지정한다.
@Component(value="tv")
@Scope("prototype")
public class SamsungTV implements TV {
	@Autowired
	@Qualifier(value="orangeSpeaker")
	private Speaker speaker;

	public void powerOn() {
		System.out.println("삼성 TV 전원 켜기");
	}
	public void powerOff() {
		System.out.println("삼성 TV 전원 끄기");
	}
	public void channelUp() {
		System.out.println("삼성 TV 채널 올리기");
	}
	public void channelDown() {
		System.out.println("삼성 TV 채널 내리기");
	}
	public void volumeUp() {
		if(speaker == null) {
			System.out.println("삼성 TV 음량 올리기");
		} else {
			speaker.volumeUp();
		}
	}
	public void volumeDown() {
		if(speaker == null) {
			System.out.println("삼성 TV 음량 내리기");
		} else {
			speaker.volumeDown();
		}
	}
}
