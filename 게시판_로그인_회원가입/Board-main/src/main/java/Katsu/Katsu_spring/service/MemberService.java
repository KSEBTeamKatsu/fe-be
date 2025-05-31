package Katsu.Katsu_spring.service;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        if (memberRepository.findById(member.getId()) != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        memberRepository.save(member);
    }
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}