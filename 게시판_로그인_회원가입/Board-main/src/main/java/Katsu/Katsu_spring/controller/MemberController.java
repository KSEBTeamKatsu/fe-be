package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.repository.JdbcMemberRepository;
import Katsu.Katsu_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Katsu.Katsu_spring.repository.MemberRepository;

import javax.sql.DataSource;
import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(DataSource dataSource) {
        JdbcMemberRepository repository = new JdbcMemberRepository(dataSource);
        this.memberService = new MemberService((MemberRepository) repository);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Member member) {
        try {
            memberService.join(member);
            return "회원가입 성공!";
        } catch (IllegalStateException e) {
            return "에러: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Member member, HttpSession session) {
        Member found = memberService.findMember(member.getId());

        if (found == null) {
            return "존재하지 않는 아이디입니다.";
        }

        if (!found.getPassword().equals(member.getPassword())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        // 로그인 성공 → 세션에 회원 정보 저장
        session.setAttribute("loginMember", found);
        return "로그인 성공!";
    }
}
