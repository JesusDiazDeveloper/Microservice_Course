package com.jesusdiaz.products_service.controllers;

import com.jesusdiaz.products_service.model.dtos.ProductDTORequest;
import com.jesusdiaz.products_service.model.dtos.ProductDTOResponse;
import com.jesusdiaz.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct (@RequestBody ProductDTORequest productDTORequest) {
        this.productService.addProduct (productDTORequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTOResponse> getAllProducts(){
        return this.productService.getAllProducts();
    }


}