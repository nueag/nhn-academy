package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.model.ProductDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/detail/{productId}")
    public String getProductDetail(@PathVariable("productId") int productId,
                                   Model model) {
        ProductDto productDto = productService.getProductDetailByProductId(productId);
        model.addAttribute("product", productDto);

        return "thymeleaf/main/productDetail";
    }

}
