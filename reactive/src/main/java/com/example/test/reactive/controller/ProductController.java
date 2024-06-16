package com.example.test.reactive.controller;

import com.example.test.reactive.model.Product;
import com.example.test.reactive.repository.ProductRepository;
import com.example.test.reactive.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Mono<Product> postProduct (@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getProduct (@RequestParam(required = false) String owner) {
        if (owner != null)
            return productService.findAllByOwner(owner);
        else
            return productService.findAll();
    }
}
