package springBoard.springboard.Service;

import springBoard.springboard.domain.Member;
import springBoard.springboard.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springBoard.springboard.Repository.MemberRepository;
import springBoard.springboard.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("손푸링");
        member.setEmail("spring1@spring.io");
        member.setMemberId("spring12");
        member.setPassword("spring");
        //when
        String saveId = memberService.join(member);
        System.out.println("세이브아이디:"+saveId);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicate() {
        //given
        Member member1 = new Member();
        member1.setName("손푸링");
        member1.setEmail("spring1@spring.io");
        member1.setMemberId("spring1");
        member1.setPassword("spring");

        Member member2 = new Member();
        member1.setName("손푸링");
        member1.setEmail("spring1@spring.io");
        member1.setMemberId("spring1");
        member1.setPassword("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다123");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
