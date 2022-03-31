package net.developia.spring01.di05;

import org.springframework.stereotype.Component;

@Component
public class MarshallSpeaker implements Speaker {
	public void volumeUp() {
		System.out.println("MarshallSpeaker : 소리 키우기");
	}
	public void volumeDown() {
		System.out.println("MarshallSpeaker : 소리를 줄이기");
	}
}