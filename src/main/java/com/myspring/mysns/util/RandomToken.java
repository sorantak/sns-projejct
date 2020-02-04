package com.myspring.mysns.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// @Component 필수(Controller에서 쓰기 때문)
@Component
public class RandomToken {
	
	private static final Logger logger = LoggerFactory.getLogger(RandomToken.class);
	
	public StringBuffer createToken() {
		logger.info("call createToken() method in RandomToken");
		
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for(int i = 0; i < 20; i ++) {
			int rIndex = rnd.nextInt(3);
			switch(rIndex) {
			case 0:
				// 97은 a-z ASCHI 코드
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// 65는 A-Z ASCHI 코드
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}

		return temp;
	}
	
}
