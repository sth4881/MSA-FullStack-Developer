package net.developia.spring01.di02;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVTest {
	public static void main(String[] args) {
		GenericApplicationContext context = new GenericXmlApplicationContext(TVTest.class, "product.xml"); // Spring Bean 생성
		System.out.println("------------------------------------------------");
		TV tv = (TV) context.getBean("tv");
		tv.powerOn();
		tv.channelUp();
		tv.channelDown();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		System.out.println("------------------------------------------------");
		TV tv2 = (TV) context.getBean("tv");
		tv2.powerOn();
		tv2.channelUp();
		tv2.channelDown();
		tv2.volumeUp();
		tv2.volumeDown();
		tv2.powerOff();
		
		System.out.println(tv.hashCode());
		System.out.println(tv2.hashCode());
	}
}
