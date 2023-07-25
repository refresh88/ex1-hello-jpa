package hellojpa;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        Member member1 = new Member(150L, "A");
        em.persist(member1);
    }

    @Test
    void 조인테스트() {
        //given
        Member member = new Member();
        member.setId(2L);
        member.setName("HelloB");

        //when
        Long savedId = memberService.join(member);

        //then
        assertThat(memberRepository.findOne(savedId)).isEqualTo(member);
    }

    @Test
    void 쓰기지연테스트() {
        //given
        // member 생성
        Member member1 = new Member(150L, "A");
        em.persist(member1);

        // member name 변경
        Member member = em.find(Member.class, 150L);
        member.setName("ZZZZZ");

        /**
         * set으로 데이터를 수정했다고 다시 persist를 해줄 필요가 없음.
          */
//        em.persist(member);

        System.out.println("==========");
    }

    @Test
    void 변경감지() {
        // member 생성
        Member member1 = new Member(150L, "A");
        em.persist(member1);

        //변경
        Member member = em.find(Member.class, 150L);
        member.setName("ZZZZZ");
    }

    @Test
    void 플러시테스트() {
        Member member = new Member(200L, "member200");
        em.persist(member);

        em.flush();
    }

    @Test
    void 준영속테스트() {
        //memeber는 지금 영속 상태임.
        Member member1 = em.find(Member.class, 150L);
        // 변경 감지
        member1.setName("AAAAA");

        // 얘는 이제 JPA가 관리 안함. 따라서 커밋해도 아무일 안일어남
        em.detach(member1);

    }

}
