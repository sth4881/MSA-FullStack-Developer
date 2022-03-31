package net.developia.spring01.di03;

public class OrangeSpeaker implements Speaker {
	public void volumeUp() {
		System.out.println("OrangeSpeaker : 소리 키우기");
	}
	public void volumeDown() {
		System.out.println("OrangeSpeaker : 소리를 줄이기");
	}
}