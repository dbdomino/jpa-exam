package com.minod.shop.service;

import com.minod.shop.domain.Member;
import com.minod.shop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 기본값은 false임. 데이터 변경이 없는 읽기전용 메서드에 사용.(DB성능향상을 위해)
public class MemberService {
//    @Autowired // 필드주입
    // final 키워드를 추가하면 컴파일 시점에 memberRepository 를 설정하지 않는 오류를 체크할 수 있다.(보통기본 생성자를 추가할 때 발견)
    private final MemberRepository memberRepository;

    // 생성자 주입. (필드 주입을 막아 Repository가 변경 불가능한 안전한 객체로 생성 가능)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Transactional // 읽기전용 해제, 이 메서드만,
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복   회원 검증
    private void validateDuplicateMember(Member member) {
        // 이름으로 중복 회원을 검증하는거라 동시성 문제 가능성이 있다. 멀티스레드 환경에서 이를 막으려면?
        // Entity에 중복회원을 검증하는 조건에 Unique 제약 조건을 걸면된다. 이름은 중복이 가능할 수 있으니, 이름보단 id나 si가 되어야겠지.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){ // List로 받은거라 isEmpty() 가능, 비었으면 true -> 안비었으면 이미 아이디가 있다. 에러 내야하니 !붙임
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // 회원 조회 1명
    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
