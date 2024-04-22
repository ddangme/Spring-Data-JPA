package ddangme.springdatajpa.dto;

import ddangme.springdatajpa.entity.Member;
import lombok.Data;

@Data
public class MemberRequest {
    private Long id;
    private String username;

    public MemberRequest(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
