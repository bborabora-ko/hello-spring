package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /* 회원가입 */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try {
            //요구사항 - 같은 이름이 있는 중복회원 X
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }



    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //-> 결과 Optional객체
                 .ifPresent(m -> {
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    /* 전체회원조회 */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }


    }

    /* 개별회원조회 */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
