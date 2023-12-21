package com.jesusdiaz.inventory_service.controllers;

import com.jesusdiaz.inventory_service.model.dtos.BaseResponse;
import com.jesusdiaz.inventory_service.model.dtos.OrderItemDTORequest;
import com.jesusdiaz.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Returns true if there is at least one inventory item with the specified SKU, and if that inventory item has a quantity greater than zero
     * @param sku
     * @return
     */
    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku){
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemDTORequest> orderItems) {
        return inventoryService.areInStock(orderItems);
    }
}
