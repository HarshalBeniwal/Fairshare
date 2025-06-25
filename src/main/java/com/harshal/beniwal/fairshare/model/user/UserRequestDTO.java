package com.harshal.beniwal.fairshare.model.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}

