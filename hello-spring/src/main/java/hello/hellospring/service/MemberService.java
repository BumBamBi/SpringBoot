package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member){

        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member){
        /*
        // 같은 이름이 있는 중복회원은 X라고 가정한다면
        Optional<Member> result = memberRepository.findByName(member.getName());
        
        // NULL이 아니라면(ifPresent)
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 위와 같은 내용 - 옵셔널 변수를 따로 안만들음
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 회원 전부 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // id로 회원 조회
    public Optional<Member> findOne(Long MemeberId){
        return memberRepository.findById(MemeberId);
    }
}
