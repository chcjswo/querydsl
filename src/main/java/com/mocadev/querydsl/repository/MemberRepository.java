package com.mocadev.querydsl.repository;

import com.mocadev.querydsl.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog http://mocadev.tistory.com
 * @github http://github.com/chcjswo
 * @since 2021-05-24
 **/
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom,
	QuerydslPredicateExecutor<Member> {

	List<Member> findByUsername(String username);

}
