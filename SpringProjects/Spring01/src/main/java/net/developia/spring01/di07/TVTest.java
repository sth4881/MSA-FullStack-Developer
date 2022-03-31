package net.developia.spring01.di07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TVTest {
	public static void main(String[] args) {
		GenericApplicationContext context =
				new AnnotationConfigApplicationContext(Product.class);
		System.out.println("---------------------------------------");
		TV tv = (TV) context.getBean("tv");
		tv.powerOn();
		tv.channelUp();
		tv.channelDown();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
