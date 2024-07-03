# Spring Reactive

## Server Sent Event
- [baeldung.com/spring-server-sent-events](https://www.baeldung.com/spring-server-sent-events)
- [server-sent-event-vs-websocket-avec-spring-webflux](https://blog.ght1pc9kc.fr/2021/server-sent-event-vs-websocket-avec-spring-webflux/)


## WebSocket
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