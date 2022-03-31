package net.developia.spring01.di06;

import org.springframework.stereotype.Component;

public class MarshallSpeaker implements Speaker {
	public void volumeUp() {
		System.out.println("MarshallSpeaker : 소리를 키웁니다.");
	}
	public void volumeDown() {
		System.out.println("MarshallSpeaker : 소리를 낮춥니다.");
	}
}