package net.developia.spring01.di02;

public class MarshallSpeaker implements Speaker {
	public MarshallSpeaker() {
		System.out.println("MarshallSpeaker() 생성자 수행");
	}
	public void volumeUp() {
		System.out.println("MarshallSpeaker : 소리를 키웁니다.");
	}
	public void volumeDown() {
		System.out.println("MarshallSpeaker : 소리를 낮춥니다.");
	}
}