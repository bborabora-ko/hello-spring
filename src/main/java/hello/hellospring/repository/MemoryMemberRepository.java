package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id를 자동으로 생성하기 위한 변수

    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // .findAny()는 stream에서 가장 먼저 탐색되는 요소를 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //.values() -> 저장된 모든 "값" 출력
        //new ArrayList<>(값) -> arraylist 초기 생성시부터 값 추가
    }

    public void clearStore(){
        store.clear(); // .clear() -> map의 모든 값 제거
    }

}
