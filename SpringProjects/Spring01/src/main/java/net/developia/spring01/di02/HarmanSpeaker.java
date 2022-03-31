package net.developia.spring01.di02;

public class HarmanSpeaker implements Speaker {
	public HarmanSpeaker() {
		System.out.println("HarmanSpeaker() 생성자 수행");
	}
	public void volumeUp() {
		System.out.println("HarmanSpeaker : 소리를 키웁니다.");
	}
	
	public void volumeDown() {
		System.out.println("HarmanSpeaker : 소리를 줄입니다.");
	}
}
