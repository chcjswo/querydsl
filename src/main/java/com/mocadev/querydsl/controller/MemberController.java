package com.mocadev.querydsl.controller;

import com.mocadev.querydsl.dto.MemberSearchCondition;
import com.mocadev.querydsl.dto.MemberTeamDto;
import com.mocadev.querydsl.repository.MemberJpaRepository;
import com.mocadev.querydsl.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	private final MemberRepository memberRepository;

	@GetMapping("/v1/members")
	public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
		return memberJpaRepository.searchByWhere(condition);
	}

	@GetMapping("/v2/members")
	public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition condition, Pageable pageable) {
		return memberRepository.searchPageSimple(condition, pageable);
	}

	@GetMapping("/v3/members")
	public Page<MemberTeamDto> searchMemberV3(MemberSearchCondition condition, Pageable pageable) {
		return memberRepository.searchPageComplex(condition, pageable);
	}

}
