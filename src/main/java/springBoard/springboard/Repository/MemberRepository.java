package springBoard.springboard.Repository;

import springBoard.springboard.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    void deleteById(String id);
    Optional<Member> findByMemberId(String MemberId);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
