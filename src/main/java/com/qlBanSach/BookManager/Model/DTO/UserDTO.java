package com.qlBanSach.BookManager.Model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    @NotNull(message = "Email hoặc mật khẩu không hợp lệ!!")
    @NotBlank(message = "Email hoặc mật khẩu không hợp lệ!!")
    @Email
    private String email;
    @NotNull(message = "Email hoặc mật khẩu không hợp lệ!!")
    @NotBlank(message = "Email hoặc mật khẩu không hợp lệ!!")
    @Size(min = 8)
    private String password;
    private String rePassword;
    private String dateOfBirth;
    private String address;
    private String firstname;
    private String lastname;
    private List<String> roles;
}
