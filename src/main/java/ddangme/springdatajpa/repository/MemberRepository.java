package ddangme.springdatajpa.repository;

import ddangme.springdatajpa.dto.MemberDTO;
import ddangme.springdatajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
