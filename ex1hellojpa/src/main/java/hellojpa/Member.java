package hellojpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Member {

    @Id
    private long id;

    private String name;
}
