package com.example.test.reactive.service;

import com.example.test.reactive.event.ProductCreation;
import com.example.test.reactive.model.Product;
import com.example.test.reactive.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Mono<Product> createProduct(Product product) {
        applicationEventPublisher.publishEvent(new ProductCreation(this, product));
        return productRepository.save(product);
    }

    public Flux<Product> findAll () {
        return productRepository.findAll();
    }

    public Flux<Product> findAllByOwner (String owner) {
        return productRepository.findByOwner(owner);
    }
}
