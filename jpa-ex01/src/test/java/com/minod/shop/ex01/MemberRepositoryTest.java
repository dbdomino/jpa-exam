package com.minod.shop.ex01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
//        memberRepository.clearStore();
    }

    @Test
    @Transactional
        // 스프링껄 쓰는걸 권장
    void testSave() {
        // Given
        Member member = new Member();
        member.setUsername("John");

        // When
        Long savedMember = memberRepository.save(member);

        // Then
        Member findMember = memberRepository.findById(savedMember);
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    @Transactional
    void testFindById() {
        // Given
        Member member1 = new Member();
        member1.setUsername("John");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setUsername("Jane");
        memberRepository.save(member2);

        // When
        Member foundMember = memberRepository.findById(member2.getId());

        // Then
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getUsername()).isEqualTo("Jane");
    }

    @Test
    void testFindByIdNotFound() {
//        // Given
//        Member member = new Member();
//        member.setName("John");
//        memberRepository.save(member);
//
//        // When
//        Optional<Member> foundMember = memberRepository.findById(Long.MAX_VALUE);
//
//        // Then
//        assertThat(foundMember).isNotPresent();
    }

    @Test
    void testFindAll() {
//        // Given
//        Member member1 = new Member();
//        member1.setName("John");
//        memberRepository.save(member1);
//
//        Member member2 = new Member();
//        member2.setName("Jane");
//        memberRepository.save(member2);
//
//        // When
//        List<Member> members = memberRepository.findAll();
//
//        // Then
//        assertThat(members).hasSize(2);
//        assertThat(members).extracting("name").containsExactly("John", "Jane");
    }

    @Test
    void testFindAllEmpty() {
//        // When
//        List<Member> members = memberRepository.findAll();
//
//        // Then
//        assertThat(members).isEmpty();
    }

    @Test
    void testDuplicateMemberSave() {
//        // Given
//        Member member1 = new Member();
//        member1.setName("John");
//        memberRepository.save(member1);
//
//        Member member2 = new Member();
//        member2.setName("John");
//
//        // When
//        assertThrows(IllegalStateException.class, () -> memberRepository.save(member2));
    }
}
