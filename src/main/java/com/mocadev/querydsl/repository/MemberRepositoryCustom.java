package com.mocadev.querydsl.repository;

import com.mocadev.querydsl.dto.MemberSearchCondition;
import com.mocadev.querydsl.dto.MemberTeamDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog http://mocadev.tistory.com
 * @github http://github.com/chcjswo
 * @since 2021-05-24
 **/
public interface MemberRepositoryCustom {

	List<MemberTeamDto> searchByWhere(MemberSearchCondition condition);
	Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
	Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);

}
