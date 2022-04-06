package net.developia.spring06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.developia.spring06.mapper.Sample1Mapper;
import net.developia.spring06.mapper.Sample2Mapper;

@Log4j
@Service
public class SampleTxServiceImpl implements SampleTxService {
	@Setter(onMethod_ = { @Autowired })
	private Sample1Mapper mapper1;
	
	@Setter(onMethod_ = { @Autowired })
	private Sample2Mapper mapper2;
	
	// 메소드 내부에 포함된 모든 구문들을 하나의 트랜잭션으로 묶어서 처리할 수 있다.
	// 메소드 이외에 클래스, 인터페이스에도 붙일 수 있지만 우선순위가 낮다.
	@Override
	@Transactional
	public void addData(String value) {
		log.info("mapper..............1");
		mapper1.insertCol1(value);
		
		log.info("mapper..............2");
		mapper2.insertCol2(value);
		
		log.info("mapper..............end");
	}
}
