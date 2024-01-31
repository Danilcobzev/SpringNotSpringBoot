package org.example.dto;

import java.util.Objects;

public class UserDtoNewPassword {
    private String username;
    private String password;

    private String newPassword;

    public UserDtoNewPassword(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDtoNewPassword)) return false;
        UserDtoNewPassword that = (UserDtoNewPassword) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getNewPassword(), that.getNewPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getNewPassword());
    }

    public UserDtoNewPassword() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

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

}
