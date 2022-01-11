package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
// Optional -> Null처리 방법
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
