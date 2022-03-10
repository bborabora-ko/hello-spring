package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    @Bean //@Bean = 스프링빈에 등록해!
    public MemberService memberservice(){
        return new MemberService(memberRepository()); //스프링빈에 있는 memberRepository()를 엮어줘야하기 때문에 파라미터로 넣어줌
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}