package com.example.test.reactive.repository;

import com.example.test.reactive.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldBeSaved () {
        Product product = new Product(null, "test", "toto", "no desc");
        Product savedProduct = productRepository.save(product).block();
        assertNotNull(savedProduct, "The result is null");
        assertNotNull(savedProduct.getId(), "The id is null");
        assertEquals(product.getName(), savedProduct.getName(), "The name is not the same");
        assertEquals(product.getOwner(), savedProduct.getOwner(), "The owner is not the same");
        assertEquals(product.getDescription(), savedProduct.getDescription(), "The description is not the same");
    }

}