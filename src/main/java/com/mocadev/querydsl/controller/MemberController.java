package com.mocadev.querydsl.controller;

import com.mocadev.querydsl.dto.MemberSearchCondition;
import com.mocadev.querydsl.dto.MemberTeamDto;
import com.mocadev.querydsl.repository.MemberJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog http://mocadev.tistory.com
 * @github http://github.com/chcjswo
 * @since 2021-05-20
 **/
@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberJpaRepository memberJpaRepository;

	@GetMapping("/v1/members")
	public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
		return memberJpaRepository.searchByWhere(condition);
	}

}
