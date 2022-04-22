package hellojpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    private long id;

    private String name;
}
