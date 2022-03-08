package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드가 끝날때마다 실행되는 콜백메소드라고 생각하면 됨
    public void afterEach(){
        repository.clearStore(); //이제 메소드가 끝날때마다 store가 비워지게 됨
    }

    @Test // @Test 어노테이션 추가시 테스트코드 실행가능
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member); // store에 member객체 저장됨

        Member result = repository.findById(member.getId()).get();//리컨타입 자동생성 단축키: Ctrl + Alt + V
//        System.out.println("result = " + (result == member)); //단순히 같다 아니다 글자로 확인
        assertThat(member).isEqualTo(result); //member와 result가 같은지 검증
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //Shift + F6 : rename
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    // 테스트는 순서 보장이 되지 않는다. 테스트는 순서와 상관없이 메소드별로 따로 돌게금 설계해야한다.
    // findAll()이 먼저 실행되면서 findByName이 실행될 때는 이전에 저장되었던 것을 나오게 한 것
    // 따라서 테스트가 끝날 때마다 저장소나 공용 데이터들을 지워줘야함

}
