package springBoard.springboard.Service;

import springBoard.springboard.domain.Member;
import springBoard.springboard.Repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public String join(Member member) {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByMemberId(member.getMemberId())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteById(Member member) {
        memberRepository.deleteById(member.getMemberId());
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

}
