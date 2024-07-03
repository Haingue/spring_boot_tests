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
@RequestMapping("/service/sse/product")
public class ProductSSEController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSSEController.class);

    @Resource
    private ProductCreationHandle productCreationHandle;
    @Resource
    private ProductService productService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<ServerSentEvent<Product>> getProductTest () {
        Flux<ServerSentEvent<Product>> productProducer = productService.findAll()
                .takeLast(1).map(product -> ServerSentEvent.<Product>builder()
                        .id(UUID.randomUUID().toString())
                        .event("product")
                        .data(product)
                        .build());

        return Flux.merge(productProducer)
                .delayElements(Duration.ofMillis(500))
                .repeat();
    }
}
