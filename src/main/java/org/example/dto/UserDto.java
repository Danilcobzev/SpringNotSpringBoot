package org.example.dto;

import java.util.Objects;

public class UserDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        UserDto userDto = (UserDto) o;
        return Objects.equals(getUsername(), userDto.getUsername()) && Objects.equals(getPassword(), userDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
