package com.minod.shop.service;

import com.minod.shop.domain.Member;
import com.minod.shop.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWtih(SpringRunner.class) // Junit 4에서 사용됨.
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
//    @InjectMocks
    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("minod001");

        // When
        Long savedId = memberService.join(member); // Service에서 join하면 회원가입한 멤버id를 반환함.

        // then Assert
        assertEquals(member, memberRepository.findOne(savedId));
//        assertEquals(member.getId(), savedId);
    }

    // 예외 TC, 이름으로 중복이 있다면 예외발생
    @Test // (expected = IllegalStateException.class) 옵션은 JUnit5에서 에러남.
    void 중복회원_조회() {
        //given
        Member member1 = new Member();
        member1.setName("minod01");

        Member member2 = new Member();
        member2.setName("minod01");
        //when
        memberService.join(member1);
        memberService.join(member2); // 예외발생

        //then
        fail("예외발생하지 않음"); // assertj에서 사용함.

    }

}
