package com.qlBanSach.BookManager.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
public class ClientController {

    @GetMapping()
    public ModelAndView showHome() {
        ModelAndView view;
        view = new ModelAndView("client/home");
        return view;
    }

    @GetMapping(value = "/detail")
    public ModelAndView showLoginPage() {
        return new ModelAndView("client/item/detail");
    }

    @GetMapping(value = "/cartList")
    public ModelAndView showCartListPage() {
        return new ModelAndView("client/cart/cartList");
    }

}
