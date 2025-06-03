package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.repository.JdbcMemberRepository;
import Katsu.Katsu_spring.service.MemberService;
import Katsu.Katsu_spring.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Katsu.Katsu_spring.repository.MemberRepository;

import javax.sql.DataSource;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @Autowired
    public MemberController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
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
        Member found = memberService.findMember(member.getId());
        // 아이디/비밀번호 검증
        if (found != null && found.getPw().equals(member.getPw())) {
            // 세션 등 처리
            session.setAttribute("user", member.getId());

            // 토큰 발급 예시
            String token = jwtUtil.generateToken(member.getId());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","로그인 실패"));
        }
    }

}
