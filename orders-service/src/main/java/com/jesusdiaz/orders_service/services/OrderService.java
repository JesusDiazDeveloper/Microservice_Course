package com.jesusdiaz.orders_service.services;

import com.jesusdiaz.orders_service.model.dtos.*;
import com.jesusdiaz.orders_service.model.entities.Order;
import com.jesusdiaz.orders_service.model.entities.OrderItems;
import com.jesusdiaz.orders_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderDTORequest orderDTORequest) {

        //Check for inventory,
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderDTORequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();


        if (result != null && !result.hasError()) {

            var newOrder = new Order();
            newOrder.setOrderNumber(UUID.randomUUID().toString());
            newOrder.setOrderItems(orderDTORequest
                    .getOrderItems()
                    .stream()
                    .map(orderItemsRequest -> mapOrderDTOItemRequestToOrderItem(orderItemsRequest, newOrder))
                    .toList());
            this.orderRepository.save(newOrder);
        } else {
            throw new IllegalArgumentException("Some of the products are not in the stock");
        }
    }

    private OrderItems mapOrderDTOItemRequestToOrderItem(OrderItemDTORequest orderItemsRequest, Order newOrder) {
        return OrderItems.builder()
                .id(orderItemsRequest.getId())
                .sku(orderItemsRequest.getSku())
                .price(orderItemsRequest.getPrice())
                .price(orderItemsRequest.getPrice())
                .order(newOrder)
                .build();
    }


    public List<OrderDTORespose> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();

        return orders.stream().map(this::mapToOrderDTOResponse).toList();

    }

    private OrderDTORespose mapToOrderDTOResponse(Order order) {
        return new OrderDTORespose(order.getId(),order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemDTORequest).toList());
    }

    private OrderItemDTOResponse mapToOrderItemDTORequest(OrderItems orderItems) {
        return new OrderItemDTOResponse(orderItems.getId(),orderItems.getSku(),orderItems.getPrice(),orderItems.getQuantity());
    }


}
