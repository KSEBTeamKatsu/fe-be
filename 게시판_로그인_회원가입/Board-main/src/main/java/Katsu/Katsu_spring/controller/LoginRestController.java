package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.dto.UserDTO;
import Katsu.Katsu_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginRestController {

    private final UserService userService;

    @Autowired
    public LoginRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String id = credentials.get("id");
        String pw = credentials.get("pw");

        UserDTO user = userService.findById(id);

        if (user != null && user.getPw().equals(pw)) {
            String token = "fake-jwt-token-for-" + id;
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("message", "로그인 성공");
            return response;
        } else {
            throw new RuntimeException("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
