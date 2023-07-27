package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50) // 메모리에 시퀀스를 미리 50개 생성해놓고 갖다 쓰는것. cache랑 비슷한 개념.
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private int id;

    @Column(name = "name", nullable = false)
    private String username;

    protected Member() {
    }
}
