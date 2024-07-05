# Spring Reactive

## Server Sent Event
- [com.example.test.reactive.controller.ProductSSEController](./src/main/java/com/example/test/reactive/controller/ProductSSEController.java)


### Documentations
- [baeldung.com/spring-server-sent-events](https://www.baeldung.com/spring-server-sent-events)
- [server-sent-event-vs-websocket-avec-spring-webflux](https://blog.ght1pc9kc.fr/2021/server-sent-event-vs-websocket-avec-spring-webflux/)


## WebSocket
- [com.example.test.reactive.controller.ProductWebSocketController](./src/main/java/com/example/test/reactive/controller/ProductWebSocketController.java)
- [com.example.test.reactive.service.ProductCreationHandle](./src/main/java/com/example/test/reactive/service/ProductCreationHandle.java)

### JS Client

```javascript

const exempleSocket = new WebSocket("ws://localhost:8080/service-reactive/product", "test");
exempleSocket.onopen = (event) => {
    console.log('WebSocket open !');
}

exempleSocket.onmessage = (event) => {
    console.log('Message:', event.data);
}

```