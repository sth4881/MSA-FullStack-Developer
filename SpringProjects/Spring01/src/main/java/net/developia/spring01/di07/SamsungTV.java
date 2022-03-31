package net.developia.spring01.di07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SamsungTV implements TV {
	private Speaker speaker;
	
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
		System.out.println("SamsungTV() 생성자 실행");
	}
	
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
