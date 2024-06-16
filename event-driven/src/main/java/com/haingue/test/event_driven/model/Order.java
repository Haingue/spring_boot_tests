package com.haingue.test.event_driven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
        @Id
        UUID id;
        String owner;
        double amount;
        Instant creationDatetime;

        public Order() {
        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public String getOwner() {
                return owner;
        }

        public void setOwner(String owner) {
                this.owner = owner;
        }

        public double getAmount() {
                return amount;
        }

        public void setAmount(double amount) {
                this.amount = amount;
        }

        public Instant getCreationDatetime() {
                return creationDatetime;
        }

        public void setCreationDatetime(Instant creationDatetime) {
                this.creationDatetime = creationDatetime;
        }

        @Override
        public String toString() {
                return "Order{" +
                        "id='" + id + '\'' +
                        ", owner='" + owner + '\'' +
                        ", amount=" + amount +
                        ", creationDatetime=" + creationDatetime +
                        '}';
        }
}
