package com.enoca.challenge.customer;

import com.enoca.challenge.validator.PasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @Email(regexp = ".+@.+\\..+",message = "invalid e-mail")
        @NotBlank
        String email,

        @PasswordConstraint
        @NotBlank
        String password,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName)
{
}
