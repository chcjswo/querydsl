package com.mocadev.querydsl;

import static com.mocadev.querydsl.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

import com.mocadev.querydsl.entity.Member;
import com.mocadev.querydsl.entity.QMember;
import com.mocadev.querydsl.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog http://mocadev.tistory.com
 * @github http://github.com/chcjswo
 * @since 2021-05-11
 **/
@SpringBootTest
@Transactional
public class QuerydslBasicTest {

	@Autowired
	EntityManager em;

	JPAQueryFactory queryFactory;

	@BeforeEach
	public void before() {
		queryFactory = new JPAQueryFactory(em);

		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		em.flush();
		em.clear();
	}

	@Test
	public void jpqlTest() {
		String queryString = "select m from Member m where m.username = :username";
		Member findMember = em
			.createQuery(queryString, Member.class)
			.setParameter("username", "member1")
			.getSingleResult();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	public void querydslTest() {
		Member findMember = queryFactory
			.select(member)
			.from(member)
			.where(member.username.eq("member1"))
			.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	void searchTest() {
		Member findMember = queryFactory
			.selectFrom(QMember.member)
			.where(QMember.member.username.eq("member1")
				.and(QMember.member.age.eq(10)))
			.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	void searchAndParamTest() {
		Member findMember = queryFactory
			.selectFrom(QMember.member)
			.where(
				member.username.eq("member1"),
				member.age.eq(10)
			)
			.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	void resultFetchTest() {
//		List<Member> list = queryFactory
//			.selectFrom(member)
//			.fetch();
//
//		Member fetchOne = queryFactory
//			.selectFrom(QMember.member)
//			.fetchOne();
//
//		Member fetchFirst = queryFactory
//			.selectFrom(QMember.member)
//			.fetchFirst();

		QueryResults<Member> results = queryFactory
			.selectFrom(member)
			.fetchResults();

		List<Member> content = results.getResults();

		for (Member mem : content) {
			System.out.println("mem = " + mem);
		}

		assertThat(results.getTotal()).isEqualTo(4);
	}

	@Test
	void sortTest() {
		em.persist(new Member(null, 100));
		em.persist(new Member("member5", 100));
		em.persist(new Member("member6", 100));

		List<Member> result = queryFactory
			.selectFrom(member)
			.where(member.age.eq(100))
			.orderBy(member.age.desc(), member.username.asc().nullsLast())
			.fetch();

		Member member5 = result.get(0);
		Member member6 = result.get(1);
		Member memberNull = result.get(2);

		assertThat(member5.getUsername()).isEqualTo("member5");
		assertThat(member6.getUsername()).isEqualTo("member6");
		assertThat(memberNull.getUsername()).isNull();
	}

	@Test
	void pagingTest() {
		List<Member> result = queryFactory
			.selectFrom(member)
			.orderBy(member.username.desc())
			.offset(1)
			.limit(2)
			.fetch();

		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	void pagingResultsTest() {
		QueryResults<Member> results = queryFactory
			.selectFrom(member)
			.orderBy(member.username.desc())
			.offset(1)
			.limit(2)
			.fetchResults();

		assertThat(results.getTotal()).isEqualTo(4);
		assertThat(results.getLimit()).isEqualTo(2);
		assertThat(results.getOffset()).isEqualTo(1);
		assertThat(results.getResults().size()).isEqualTo(2);
	}

}
