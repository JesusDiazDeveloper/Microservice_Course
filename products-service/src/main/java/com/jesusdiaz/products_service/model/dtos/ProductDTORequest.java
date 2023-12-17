package com.jesusdiaz.products_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTORequest {

    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;
}
