package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.DTO.PaginationDTO;
import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import com.qlBanSach.BookManager.Service.Book.IBookService;
import com.qlBanSach.BookManager.Service.Cart.ICartService;
import com.qlBanSach.BookManager.Utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class ClientController {
    private final IBookService bookService;
    private final ICartService cartService;
    private final Pagination pagination;
    @GetMapping()
    public ModelAndView showHome(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "limit", required = false) Integer limit) {
        ModelAndView view;
        view = new ModelAndView("client/home");
        view.addObject("books", bookService.getBookByName(name, page, limit));
        Pageable pageable = pagination.pageUtil(page, limit);
        view.addObject("pagination", PaginationDTO.builder()
                .page(pageable.getPageNumber() + 1)
                .limit(pageable.getPageSize())
                .totalPages((int) Math.ceil(bookService.getAllBooks().size() * 1.0 / pageable.getPageSize()))
                .build()
        );
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

}
