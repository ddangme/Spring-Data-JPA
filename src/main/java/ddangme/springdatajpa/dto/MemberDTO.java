package ddangme.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDTO {
    Long id;
    String username;
    String teamName;

}
