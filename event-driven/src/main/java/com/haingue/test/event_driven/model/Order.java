package com.haingue.test.event_driven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {
        @Id
        String id;
        String owner;
        double amount;
        Instant creationDatetime;

        public Order() {
        }

        public static class Builder {

                String id;
                String owner;
                double amount;
                Instant creationDatetime;
                public Builder id (String id) {
                        this.id = id;
                        return this;
                }
                public Builder owner (String owner) {
                        this.owner = owner;
                        return this;
                }
                public Builder amount (double amount) {
                        this.amount = amount;
                        return this;
                }
                public Builder creationDatetime (Instant creationDatetime) {
                        this.creationDatetime = creationDatetime;
                        return this;
                }
                public Order build() {
                        Order order = new Order();
                        order.id = this.id;
                        order.owner = this.owner;
                        order.amount = this.amount;
                        order.creationDatetime = this.creationDatetime;
                        return order;
                }
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
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
