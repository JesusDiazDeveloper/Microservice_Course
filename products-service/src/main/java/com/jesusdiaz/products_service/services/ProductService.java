package com.jesusdiaz.products_service.services;

import com.jesusdiaz.products_service.model.dtos.ProductDTORequest;
import com.jesusdiaz.products_service.model.dtos.ProductDTOResponse;
import com.jesusdiaz.products_service.model.entities.Product;
import com.jesusdiaz.products_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // injection
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void addProduct (ProductDTORequest productDTO){
        var product = Product.builder()
                .sku(productDTO.getSku())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .status(productDTO.getStatus())
                .build();

        this.productRepository.save(product);

        log.info("Product added: {}" , product);
    }


    public List<ProductDTOResponse> getAllProducts() {
        var products = productRepository.findAll();
        return products.stream().map(this::mapToProductDTOResponse).toList();
    }

    // TODO: Use Jackson library to map the DTOs.
    private ProductDTOResponse mapToProductDTOResponse (Product product) {
        return ProductDTOResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
