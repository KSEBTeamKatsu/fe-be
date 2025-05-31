package Katsu.Katsu_spring.domain;

public class Member {
    private Long id;        // 아이디 (번호)
    private String password;  // 비밀번호

    public Member() {}

    public Member(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}