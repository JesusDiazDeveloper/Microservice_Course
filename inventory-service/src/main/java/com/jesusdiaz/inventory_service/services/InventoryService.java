package com.jesusdiaz.inventory_service.services;

import com.jesusdiaz.inventory_service.model.dtos.BaseResponse;
import com.jesusdiaz.inventory_service.model.dtos.OrderItemDTORequest;
import com.jesusdiaz.inventory_service.model.entities.Inventory;
import com.jesusdiaz.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * returns if exist one order with the specified SKU
     */
    public boolean isInStock(String sku) {
        Optional<Inventory> inventory = inventoryRepository.findBySku(sku);
        return inventory.filter(value -> value.getQuantity() > 0 ).isPresent();
    }

    /**
     * returns if exist one order with the specified SKU
     */
    public BaseResponse areInStock(List<OrderItemDTORequest> orderItems) {

        var errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream().map(OrderItemDTORequest::getSku).toList();

        List <Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory =  inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();
            if (inventory.isEmpty()) {
                errorList.add("Product with sku " + orderItem.getSku() + " does not exist");
            }
            else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                    errorList.add("Product with sku orderItem.getSku() has insufficient quantity");
                }
            });

        /**
         * errorList.toArray(new String[0]) transform errorList(ArrayList) into String [ ] Array,
         * that is the data type that BaseResponse class receives
         * or returns a BaseResponse with null inside
         */
        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }
}
