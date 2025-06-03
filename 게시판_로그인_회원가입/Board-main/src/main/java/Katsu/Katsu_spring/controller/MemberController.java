package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public String signup(@RequestBody Member member) {
        try {
            memberService.join(member);
            return "회원가입 성공!";
        } catch (IllegalStateException e) {
            return "에러: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member, HttpSession session) {
        System.out.println("받은 ID: "+member.getId());
        System.out.println("받은 PW: "+member.getPw());
        Member found = memberService.findMember(member.getId());
        // 아이디/비밀번호 검증
        if (found != null && found.getPw().equals(member.getPw())) {
            // 세션 등 처리
            session.setAttribute("user", member.getId());
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","로그인 실패"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }

}
