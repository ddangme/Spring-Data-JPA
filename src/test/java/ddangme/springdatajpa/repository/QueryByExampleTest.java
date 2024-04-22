package ddangme.springdatajpa.repository;

import ddangme.springdatajpa.entity.Member;
import ddangme.springdatajpa.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryByExampleTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void basic() throws Exception {
        // given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        em.persist(new Member("member1", 0, teamA));
        em.persist(new Member("member2", 0, teamA));

        em.flush();

        // when
        // Probe 생성
        Member member = new Member("member1");
        Team team = new Team("team"); // 내부 조인으로 teamA 가능
        member.setTeam(team);

        // ExampleMatcher 생성, age 프로퍼티는 무시
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("age");

        Example<Member> example = Example.of(member, matcher);

        List<Member> result = memberRepository.findAll(example);

        // then
        assertThat(result.size()).isEqualTo(1);
    }
}
