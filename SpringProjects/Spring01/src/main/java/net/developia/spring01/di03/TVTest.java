package net.developia.spring01.di03;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVTest {
	public static void main(String[] args) {
		GenericApplicationContext context = new GenericXmlApplicationContext(TVTest.class, "product.xml");
		TV tv = (TV) context.getBean("tv");
		tv.powerOn();
		tv.channelUp();
		tv.channelDown();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
