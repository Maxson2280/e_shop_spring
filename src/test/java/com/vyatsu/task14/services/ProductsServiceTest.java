package com.vyatsu.task14.services;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.IProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {
    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductsService productsService;

    @Test
    @DisplayName("Should not update the product quantity when the product does not exist")
    void updateProductQuantityWhenProductDoesNotExist() {
        Long productId = 1L;
        int quantity = 10;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> productsService.updateProductQuantity(productId, quantity));

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should update the product quantity when the product exists")
    void updateProductQuantityWhenProductExists() {
        Long productId = 1L;
        int newQuantity = 10;
        Product product = new Product();
        product.setId(productId);
        product.setQuantity(5);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productsService.updateProductQuantity(productId, newQuantity);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
        assertEquals(newQuantity, product.getQuantity());
    }
}