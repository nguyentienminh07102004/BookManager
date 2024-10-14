package com.qlBanSach.BookManager.Model.Response;

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
public class UserResponse {
    private String id;
    private String fullName;
    private String address;
    private String dateOfBirth;
    private String email;
    private List<String> roles;
}
