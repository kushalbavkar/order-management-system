package com.kb.workflow.helper;

import com.kb.common.dto.products.ProductsResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.workflow.client.ProductServiceClient;
import com.kb.workflow.exceptions.WorkflowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsClientHelper {
    private final ProductServiceClient productServiceClient;

    public ProductsClientHelper(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public ProductsResponseDto getProduct(final CreateOrderDto order) {
        final ResponseEntity<List<ProductsResponseDto>> response = productServiceClient.getProducts();

        if (response.getStatusCode() != HttpStatus.OK)
            throw new WorkflowException("Failed to get productName list");

        final ProductsResponseDto product = filterProduct(response.getBody(), order);

        if (!product.inStock())
            throw new WorkflowException("Product [" + order.productName() + "] is out of stock");

        return product;
    }

    private static ProductsResponseDto filterProduct(final List<ProductsResponseDto> products, final CreateOrderDto order) {
        return products.stream()
                .filter(product -> product.name().equals(order.productName()))
                .findFirst()
                .orElseThrow(() -> new WorkflowException("Invalid productName [" + order.productName() + "] specified"));
    }
}
