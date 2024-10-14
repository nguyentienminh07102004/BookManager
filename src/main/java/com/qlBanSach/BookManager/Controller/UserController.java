package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.DTO.UserDTO;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Model.Response.UserResponse;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Service.User.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping(value = "/users/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping(value = "/users/register")
    public APIResponse registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult != null && bindingResult.hasErrors()) {
            throw new DataInvalidException(bindingResult.getFieldError().getDefaultMessage());
        }
        UserResponse response = userService.save(userDTO);
        return APIResponse.builder()
                .message("Đăng ký thành công!!")
                .response(response)
                .build();
    }

    @GetMapping(value = "/users/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/admin/users/list")
    public ModelAndView listUser(@RequestParam(value = "currentPage", required = false) Integer currentPage,
                                 @RequestParam(value = "limit", required = false) Integer limit) {
        ModelAndView view = new ModelAndView("admin/users/list");
        List<UserResponse> listUsers = userService.getAllUsers(currentPage, limit);
        view.addObject("listUsers", listUsers);
        return view;
    }
}
