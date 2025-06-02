package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.repository.JdbcMemberRepository;
import Katsu.Katsu_spring.service.MemberService;
import Katsu.Katsu_spring.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Katsu.Katsu_spring.repository.MemberRepository;

import javax.sql.DataSource;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        //JdbcMemberRepository repository = new JdbcMemberRepository(memberService);
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
    public Map<String, String> login(@RequestBody Member member) {
        Member found = memberService.findMember(member.getId());
        Map<String, String> response = new HashMap<>();

        if (found == null) {
            response.put("message", "존재하지 않는 아이디입니다.");
            return response;
        }

        if (!found.getPassword().equals(member.getPassword())) {
            response.put("message", "비밀번호가 일치하지 않습니다.");
            return response;
        }

        // ✅ JWT 생성
        String token = JwtUtil.generateToken(found.getId());
        response.put("token", token);
        response.put("message", "로그인 성공");
        return response;
    }
}
