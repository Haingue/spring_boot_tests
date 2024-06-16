package com.haingue.test.event_driven.listener;

import com.haingue.test.event_driven.event.OrderCreationEvent;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderCreationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreationListener.class);

    @Resource
    private PulsarTemplate<OrderCreationEvent> stringTemplate;
    private static final String STRING_TOPIC = "ORDER";

//    @EventListener
//    @Async
    @ApplicationModuleListener
    public void onOrderCreationEvent (OrderCreationEvent event) throws InterruptedException {
        LOGGER.info("New order created: {}", event.getOrder());

        Thread.sleep(15_000);

        // Send event to Kafka/Pulsar/MQTT/...
        stringTemplate.send(STRING_TOPIC, event);
    }

}
