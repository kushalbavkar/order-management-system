package com.kb.workflow.helper;

import com.kb.common.dto.products.ProductsResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.workflow.client.ProductServiceClient;
import com.kb.workflow.exceptions.DownstreamException;
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
        final List<ProductsResponseDto> products;

        try {
            final ResponseEntity<List<ProductsResponseDto>> response = productServiceClient.getProducts();
            products = response.getBody();
        } catch (DownstreamException ex) {
            throw new WorkflowException("Failed to get product list, Details: " + ex.getMessage());
        }

        return filterProduct(products, order);
    }

    private static ProductsResponseDto filterProduct(final List<ProductsResponseDto> products, final CreateOrderDto order) {
        final ProductsResponseDto product = products.stream()
                .filter(p -> p.name().equals(order.productName()))
                .findFirst()
                .orElseThrow(() -> new WorkflowException("Invalid productName [" + order.productName() + "] specified"));

        if (!product.inStock())
            throw new WorkflowException("Product [" + order.productName() + "] is out of stock");

        return product;
    }
}
