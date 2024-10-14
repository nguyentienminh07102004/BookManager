package com.qlBanSach.BookManager.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping(value = "/")
    public ModelAndView homeAdmin() {
        return new ModelAndView("admin/dashboard");
    }
}
