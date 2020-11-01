package com.pharmacy.mock.model.responses;

public class OrderResponse {

    private OrderResponseStatus orderResponseStatus;

    public OrderResponseStatus getOrderResponseStatus() {
        return orderResponseStatus;
    }

    public OrderResponse setOrderResponseStatus(OrderResponseStatus orderResponseStatus) {
        this.orderResponseStatus = orderResponseStatus;
        return this;
    }
}
