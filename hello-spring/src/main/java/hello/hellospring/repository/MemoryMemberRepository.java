package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

    // id와 Member를 map에 저장
    private static Map<Long, Member> store = new HashMap<>();
    // id를 위해 0부터 순차적으로 저장하는 변수
    private static long sequence = 0L;

    @Override
    // id 저장 후, map에 id와 해당 member를 저장  
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // map에서 id를 가져온 값이 null일 수 있으므로(반환값이 옵셔널)
        // Optional.ofNullable로 감싸서 리턴
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다 사용
        // store을 쭉 돌다가 입력받은 member가 map에 저장되어있던 memeber와 동일하면 리턴
        // 다만 findAny()로 인해 아무값도 없으면 Optional에 Null로 리턴
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // map에 있는 values(member들)을 리스트로 반환
        return new ArrayList<>(store.values());
    }

    

    
    
}
