package com.example.test.reactive.controller;

import com.example.test.reactive.model.Product;
import com.example.test.reactive.service.ProductCreationHandle;
import com.example.test.reactive.service.ProductService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/service/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private ProductCreationHandle productCreationHandle;
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
