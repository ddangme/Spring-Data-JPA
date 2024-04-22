package ddangme.springdatajpa.controller;

import ddangme.springdatajpa.dto.MemberRequest;
import ddangme.springdatajpa.entity.Member;
import ddangme.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Member> list(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @GetMapping("/members")
    public Page<MemberRequest> list2(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberRequest::new);
    }
}
