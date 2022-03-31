package net.developia.spring01.di07;

import org.springframework.stereotype.Component;

public class MarshallSpeaker implements Speaker {
	public MarshallSpeaker() {
		System.out.println("MarshalSpeaker() 생성자 실행");
	}
	public void volumeUp() {
		System.out.println("MarshallSpeaker : 소리를 키웁니다.");
	}
	public void volumeDown() {
		System.out.println("MarshallSpeaker : 소리를 낮춥니다.");
	}
}