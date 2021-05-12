package com.mocadev.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog http://mocadev.tistory.com
 * @github http://github.com/chcjswo
 * @since 2021-05-13
 **/
@Data
@NoArgsConstructor
public class MemberDto {

	private String username;
	private int age;

	public MemberDto(String username, int age) {
		this.username = username;
		this.age = age;
	}

}
