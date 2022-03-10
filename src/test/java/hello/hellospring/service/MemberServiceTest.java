package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //이제 메소드가 끝날때마다 store가 비워지게 됨
    }

    @Test
    void 회원가입() {
        //given -> 이런 상황이 주어졌을 때(=이 데이터를 기반으로 하는구나!)
        Member member = new Member();
        member.setName("spring");

        //when -> 이거를 실행했을 때(=이걸 검증하고 싶구나!)
        Long saveId = memberService.join(member);

        //then -> 결과가 이렇게 나와야한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    //정상코드인지 테스트하는 것도 중요하지만, 예외발생시 예외상황이 터지는지 확인하는것도 중요하다.
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

       /* //when
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        }*/
        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}