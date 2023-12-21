package com.jesusdiaz.orders_service.controllers;

import com.jesusdiaz.orders_service.model.dtos.OrderDTORequest;
import com.jesusdiaz.orders_service.model.dtos.OrderDTORespose;
import com.jesusdiaz.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderDTORequest orderDTORequest){
        this.orderService.placeOrder(orderDTORequest);
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTORespose> getAllOrders(){
        return this.orderService.getAllOrders();
    }


}
