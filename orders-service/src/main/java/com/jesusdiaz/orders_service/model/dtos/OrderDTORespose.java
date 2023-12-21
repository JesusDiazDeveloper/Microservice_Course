package com.jesusdiaz.orders_service.model.dtos;

import java.util.List;

public record OrderDTORespose(Long id , String orderNumber, List<OrderItemDTOResponse> orderItems ) {

}
