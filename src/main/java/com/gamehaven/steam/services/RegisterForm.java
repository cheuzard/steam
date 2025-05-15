package com.gamehaven.steam.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class RegisterForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 60)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Please repeat the password")
    private String confirmPassword;

    /** Called by the controller */
    public boolean passwordsMatch() {
        return Objects.equals(password, confirmPassword);
    }

}