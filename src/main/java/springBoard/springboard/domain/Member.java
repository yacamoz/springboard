package springBoard.springboard.domain;

public class Member {

    private Long memnum;
    private String name;
    private String password;
    private String memberId;
    private String email;

    public Long getMemnum() {
        return memnum;
    }

    public void setMemnum(Long memnum) {
        this.memnum = memnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
