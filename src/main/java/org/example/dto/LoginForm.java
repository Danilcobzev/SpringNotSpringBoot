package org.example.dto;

public class LoginForm {
    private String username;
    private boolean active;

    public LoginForm(String username, boolean active) {
        this.username = username;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
