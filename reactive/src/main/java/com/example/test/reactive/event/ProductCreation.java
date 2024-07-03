package com.example.test.reactive.event;

import com.example.test.reactive.model.Product;
import org.springframework.context.ApplicationEvent;

public class ProductCreation extends ApplicationEvent {
    private final Product product;

    public ProductCreation(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
