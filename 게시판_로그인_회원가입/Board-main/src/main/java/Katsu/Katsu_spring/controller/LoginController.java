package Katsu.Katsu_spring.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // 로그인 페이지 (직접 만들거나 기본 페이지 사용)
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // resources/templates/login.html
    }

    // 로그인 성공 후 메인 페이지
    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        if (oauth2User != null) {
            model.addAttribute("name", oauth2User.getAttribute("nickname"));  // 카카오 닉네임 예시
            // 네이버는 user-info 구조 다르니 확인 필요
        } else {
            model.addAttribute("name", "손님");
        }
        return "main";  // resources/templates/main.html
    }
}