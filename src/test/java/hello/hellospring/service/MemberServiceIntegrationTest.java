package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행
@Transactional
//테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
//테스트 완료 후에 항상 롤백한다.(테스트 건건마다)
//이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest { //통합테스트-자바코드와 스프링 다같이 테스트 하는 것

   @Autowired MemberService memberService;
   @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given -> 이런 상황이 주어졌을 때(=이 데이터를 기반으로 하는구나!)
        Member member = new Member();
        member.setName("spring1");

        //when -> 이거를 실행했을 때(=이걸 검증하고 싶구나!)
        Long saveId = memberService.join(member);

        //then -> 결과가 이렇게 나와야한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    //정상코드인지 테스트하는 것도 중요하지만, 예외발생시 예외상황이 터지는지 확인하는것도 중요하다.
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}