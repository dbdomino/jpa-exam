package com.minod.shop.web.mem;

import com.minod.shop.domain.Member;
import com.minod.shop.domain.valuetype.Address;
import com.minod.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/** @GetMapping(value = "/members/new") createForm
 *  @PostMapping(value = "/members/new") create
 *  @GetMapping(value = "/members") list
 */
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new") // 회원등록 페이지
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    @PostMapping(value = "/members/new") // 회원등록 수행
    public String create(@Valid MemberForm form, BindingResult result) {
        // @Valid 에서 검증실패시 오류발생, 400뜸. 이를 방지하기위해 Binding Result 사용
        if (result.hasErrors()) { // BindingResult result -> 스프링제공, MemberForm form 에서 @Valid오류가 있으면 result로 받음
            // @Valid 오류가 있으면 오류내용을 value에 포함해서 view페이지에 넣어줌.
            return "members/createMemberForm";
        }

        // 값타입 객체도 만들어서 entity 생성에 넣어버림.
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        // 서비스 실행
        memberService.join(member);

        return "redirect:/";
    }

    //추가
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // 여기서 Member는 Entity 객체이다.. Entity객체를 바로 반환하는건 안좋다.
        // 서비스 단에서 DTO로 변환, 반환해서 Attribute로 넘기는게 좋다. 지금은 심플하게하려고 이렇게함.
        // API만들때는 절대로 DTO로 변환해서 컨트롤러에 넣어 반환하자.
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
