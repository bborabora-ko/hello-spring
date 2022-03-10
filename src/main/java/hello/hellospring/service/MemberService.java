package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Service 입력시 스프링이 올라올 때 스프링이 '서비스네?' 하고 스프링컨테이너에 스프링빈으로 등록
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /* 회원가입 */
    public Long join(Member member){
        //요구사항 - 같은 이름이 있는 중복회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //-> 결과 Optional객체
                 .ifPresent(m -> {
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    /* 전체회원조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /* 개별회원조회 */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
