package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    private Long id;
    private String name;

    protected Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
