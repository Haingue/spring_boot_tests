package com.example.test.reactive.service;

import com.example.test.reactive.controller.ProductWebSocketController;
import com.example.test.reactive.event.ProductCreation;
import com.example.test.reactive.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductCreationHandle implements WebSocketHandler {
    private static final Set<WebSocketSession> sessions = new HashSet<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductWebSocketController.class);

    @Autowired
    private ProductService productService;

    @Async
    @EventListener
    public void onApplicationEvent(ProductCreation event) {
        LOGGER.info("[Event={}] Product: {}", event.getClass().getName(), event.getProduct());
        sessions.forEach(session -> {
            if (session.isOpen()) {
                Flux<Product> eventFlux = productService.findAll().takeLast(1);

                session.send(eventFlux
                        .map(Product::toString)
                        .map(session::textMessage)).block();
            } else {
                sessions.remove(session);
            }
        });
    }

    @Override
    public List<String> getSubProtocols() {
        return List.of("product-creation");
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        sessions.add(session);
        Flux<WebSocketMessage> receiveFlux = session.receive()
                .log()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(msgString -> {
                    if (msgString.equalsIgnoreCase("products")) return productService.findAll().map(Product::toString);
                    else if (msgString.equalsIgnoreCase("product-last"))
                        return productService.findAll().takeLast(1).map(Product::toString);
                    return Flux.just(msgString);
                })
                .map(session::textMessage)
                .log();
        return session.send(receiveFlux);
    }

    private Mono<Void> sendAllProducts (WebSocketSession session) {
        Flux<Product> eventFlux = productService.findAll();

        return session.send(eventFlux
                .map(Product::toString)
                .map(session::textMessage));
    }

    private Mono<Void> sendLastProducts (WebSocketSession session) {
        Flux<Product> eventFlux = productService.findAll().takeLast(1);

        return session.send(eventFlux
                .map(Product::toString)
                .map(session::textMessage));
    }
}
