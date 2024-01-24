package org.example.dto;

import java.util.Objects;

public class UserPOJO {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        UserPOJO userPOJO = (UserPOJO) o;
        return Objects.equals(getUsername(), userPOJO.getUsername()) && Objects.equals(getPassword(), userPOJO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    public UserPOJO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
