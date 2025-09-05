package org.example.dto.auth;

public class UserDto {
    private Long id;
    private String email;

    public UserDto() {}
    public UserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
}