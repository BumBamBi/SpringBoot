package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 굳이 public할 필요 없음.
class MemoryMemberRepositoryTest {
    // 테스트할 객체 생성
    // 인터페이스이지만 객체생성가능?
    MemberRepository repository = new MemoryMemberRepository();

    // 테스트 가능
    // 이름은 안헷갈리게 동일하게 한거임
    @Test
    public void save(){
        // 멤버 객체 생성 후 Name 설정
        Member member = new Member();
        member.setName("spring");

        // 멤버 객체 repository에 저장
        repository.save(member);

        // repository에서 findById()와 member.id를 통해 검색
        // 옵셔널에서 꺼낼땐 get() 사용 가능
        Member result = repository.findById(member.getId()).get();
        
        // 그 결과가 동일하면 잘 수행 된 것임
        // 디버그창에서 확인 가능
        System.out.println("result = " + (member == result));
    
        // 매번 출력 text를 비교할 수 없으니
        // 좌측의 test탭에서 error로 바로 확인 가능
        // 동일하면 문제x, 다르면 error 발생
        Assertions.assertEquals(member, result); // null이면 에러

    }
}
