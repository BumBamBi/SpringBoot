package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 굳이 public할 필요 없음.
class MemoryMemberRepositoryTest {
    // 테스트할 객체 생성
    // 인터페이스이지만 객체생성가능?
    MemoryMemberRepository repository = new MemoryMemberRepository();

    
    // Test순서에 따라 repository에 이미 값이 있어서 에러가 발생할 수 있음 -> 의존관계가 없어야함 -> repository를 초기화 해줘야함.
    // 매번 테스트가 끝나면 이곳으로 옴 -> 초기화 해줘야함.
    // 초기화를 위해 MemoryMemberRepository() 객체에 초기화 메서드 추가
    @AfterEach
    public void agterEach(){
        repository.clearStore();
    }

    // 테스트 가능
    @Test
    // 이름은 안헷갈리게 동일하게 한거임
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

    @Test
    public void findByName(){
        // member1 생성 및 Name 설정 후 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        // member2 생성 및 Name 설정 후 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // repository에서 findByName()과 Name을 통해 검색
        Member result = repository.findByName("spring1").get(); // spring2이면 아랫줄에서 에러
        
        Assertions.assertEquals(member1, result); 
    }

    @Test
    public void findAll(){
        
        // member1 생성 및 Name 설정 후 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        // member2 생성 및 Name 설정 후 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertEquals(result.size(), 2);
    }
}
