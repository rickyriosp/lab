package com.riosr.sq_ch8.controllers;

import com.riosr.sq_ch8.model.Product;
import com.riosr.sq_ch8.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //@GetMapping("/products")
    @RequestMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products.html";
    }

    //@PostMapping("/products")
    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name, @RequestParam double price, Model model) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.addProduct(product);
        model.addAttribute("products", productService.findAll());
        return "products.html";
    }
}
