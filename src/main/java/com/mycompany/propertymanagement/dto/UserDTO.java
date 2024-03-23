package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;

    private String name;

    @NotNull(message = "Email required")
    @NotEmpty(message="Email cannot be empty")
    @Size(min=20, max=60, message="Email should be between 20 and 60.")
    private String email;

    @NotNull(message ="Phone number required")
    @NotEmpty(message="Phone number cannot be empty")
    @Size(min=11, max=11, message="Phone number must be 11 digits")
    private String phoneNumber;

    @NotNull(message="Password required")
    @NotEmpty(message="Password cannot be empty")
    @Size(min=8, max=16, message="Password must be between 8 and 16 digits.")
    private String password;
}
