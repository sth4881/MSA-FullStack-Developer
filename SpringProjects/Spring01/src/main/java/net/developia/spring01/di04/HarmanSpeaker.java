package net.developia.spring01.di04;

import org.springframework.stereotype.Component;

@Component // HarmanSpeaker를 Spring Bean에 등록한다.
public class HarmanSpeaker implements Speaker {
	public void volumeUp() {
		System.out.println("HarmanSpeaker : 소리 키우기");
	}
	public void volumeDown() {
		System.out.println("HarmanSpeaker : 소리를 줄이기");
	}
}