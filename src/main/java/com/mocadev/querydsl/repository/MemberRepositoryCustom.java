package com.mocadev.querydsl.repository;

import com.mocadev.querydsl.dto.MemberSearchCondition;
import com.mocadev.querydsl.dto.MemberTeamDto;
import java.util.List;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog http://mocadev.tistory.com
 * @github http://github.com/chcjswo
 * @since 2021-05-24
 **/
public interface MemberRepositoryCustom {

	List<MemberTeamDto> searchByWhere(MemberSearchCondition condition);

}
