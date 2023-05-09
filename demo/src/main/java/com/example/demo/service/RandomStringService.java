package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class RandomStringService {
	private final static int GENERATE_NUM = 10;
	
	// ランダム文字列サービスクラス
	public List<String> generate(int charLength, boolean withNumber) {
		// 生成した文字列を保存するList
		List<String> list = new ArrayList<>();
		// 生成する個数分繰り返す
		for(int i = 0; i < GENERATE_NUM; i++) {
			if(withNumber) {
				// 数字を含む文字列をListに追加
				list.add(RandomStringUtils.randomAlphanumeric(charLength));
			} else {
				// 数字を含まない文字列をListに追加
				list.add(RandomStringUtils.randomAlphabetic(charLength));
			}
		}
		return list;
	}
}
