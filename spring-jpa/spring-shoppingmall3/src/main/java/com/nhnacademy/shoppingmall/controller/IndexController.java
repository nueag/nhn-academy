package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IndexController {
    private final ProductService productService;

    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index/{page}")
    public String indexPage(@PathVariable("page") int page,
                            Model model) {
        int size = 9;
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAllPage(pageable);
        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("noOfPages", pageable.getPageSize());
        log.debug("total: {}", pageable.getPageSize());

        return "thymeleaf/main/index";
    }

    @GetMapping("/index/{category}/{page}")
    public String categoryPage(@PathVariable("category") int categoryId,
                               @PathVariable("page") int page,
                               Model model) {
        int size = 9;
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getProductsByCategory(pageable, categoryId);
        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("noOfPages", pageable.getPageSize());
        log.debug("total: {}", pageable.getPageSize());

        return "thymeleaf/main/index";
    }

}