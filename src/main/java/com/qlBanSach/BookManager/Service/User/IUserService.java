package com.qlBanSach.BookManager.Service.User;

import com.qlBanSach.BookManager.Model.DTO.UserDTO;
import com.qlBanSach.BookManager.Model.Response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse save(UserDTO userDTO);
    List<UserResponse> getAllUsers(Integer page, Integer size);
}
