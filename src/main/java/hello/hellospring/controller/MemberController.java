package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
//기능은 없지만 스프링 처음에 뜰 때 '스프링 컨테이너'란 스프링 통이 생성됨
//거기에 @Controller가 있으면 MemberController객체를 생성해서 스프링에 넣어둠
//그리고 스프링이 관리함
//=이것을 스프링 컨테이너에서 스프링 빈이 관리된다고 표현함
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //스프링컨테이너에 등록하고(딱 하나만 등록됨) 사용하는 것이 좋음
        this.memberService = memberService;
    }


    /*
    의존성 주입 3가지 방법
    1. 필드 주입 -> 단점: 중간에 바꿀 수 있는 방법이 없음
    @Autowired private MemberService memberService;

    2. setter 주입 -> 단점: public이기 때문에 노출됨
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    3. 생성자 주입(가장 추천) -> 조립시점에 만들어져서 끝내는것
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    * */

}
