package Katsu.Katsu_spring.domain;

import lombok.Getter;
import lombok.Setter;

public class Member {
    @Setter
    @Getter
    private String id;        // 아이디 (번호)
    private String pw;  // 비밀번호

    public Member() {}

    public Member(String id, String password) {
        this.id = id;
        this.pw = password;
    }

    public String getPassword() { return pw; }
    public void setPassword(String password) { this.pw = password; }
}