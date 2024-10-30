package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import com.qlBanSach.BookManager.Service.Book.IBookService;
import com.qlBanSach.BookManager.Service.Cart.ICartService;
import com.qlBanSach.BookManager.Service.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class ClientController {
    private final IBookService bookService;
    private final ICartService cartService;
    private final IUserService userService;
    @GetMapping()
    public ModelAndView showHome(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "12") int size) {
        ModelAndView view;
        view = new ModelAndView("client/home");
        Pageable pageable = PageRequest.of(page, size);
        Page<BookResponse> books = bookService.getAllBooks(pageable);
        view.addObject("books", books);
        return view;
    }

    @GetMapping(value = "detail/{bookId}")
    public ModelAndView bookDetail(@PathVariable(value = "bookId") String id) {
        ModelAndView view = new ModelAndView("client/item/detail");
        BookResponse book = bookService.getBookById(id);
        view.addObject("book", book);
        return view;
    }

    @GetMapping(value = "carts/cartList")
    public ModelAndView showCartListPage() {
        ModelAndView view = new ModelAndView("client/cart/cartList");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CartEntity cart = cartService.getCart(email);
        view.addObject("cart", cart);
        return view;
    }

    @GetMapping("search")
    public ModelAndView search(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "12") int size,
                               @RequestParam(defaultValue = "") String keyword) {
        ModelAndView view;
        System.out.println(keyword);
        view = new ModelAndView("client/home");
        Pageable pageable = PageRequest.of(page, size);
        Page<BookResponse> books = bookService.searchBooks(keyword, pageable);
        view.addObject("books", books);
        view.addObject("keyword", keyword);
        return view;
    }

}
