package ddangme.springdatajpa.repository;

import ddangme.springdatajpa.dto.MemberDTO;
import ddangme.springdatajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedEntityGraph;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findByUsername(@Param("username") String username);

    @Query("SELECT m FROM Member m WHERE m.username= :username AND m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("SELECT m.username FROM Member m")
    List<String> findUsernameList();

    @Query("SELECT new ddangme.springdatajpa.dto.MemberDTO(m.id, m.username, t.name) FROM Member m JOIN m.team t")
    List<MemberDTO> findMemberDTO();

    @Query("SELECT m FROM Member m WHERE m.username = :name")
    Member findMembers(@Param("name") String username);

    @Query("SELECT m FROM Member m WHERE m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying
    @Query("UPDATE Member m SET m.age = m.age + 1 WHERE m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.team")
    List<Member> findMemberFetchJoin();

    // 공통 메서드 오버라이드
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    // JPQL + 엔티티 그래프
    @EntityGraph(attributePaths = {"Team"})
    @Query("SELECT m FROM Member m")
    List<Member> findMemberEntityGraph();

    // 메서드 이름으로 쿼리에서 특히 편리하다.
    @EntityGraph(attributePaths = {"team"})
    List<Member> findUsername(String username);

    @EntityGraph("Member.all")
    @Query("SELECT m FROM Member m")
    List<Member> findMemberEntityGraph2();
}
