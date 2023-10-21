package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StockServiceImpl stockServiceImpl;

    @Mock
    private StockRepository stockRepository;


    @Test
    void addProduct() {
        Stock stock = new Stock();
        Product product = new Product();
        Long idStock = 1L;

        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));

        when(productRepository.save(product)).thenReturn(product);

        Product result = productServiceImpl.addProduct(product, idStock);

        verify(stockRepository).findById(idStock);
        verify(productRepository).save(product);

        assertEquals(stock, product.getStock());
        assertEquals(product, result);

    }

    @Test
    void retrieveProduct() {
        Product mockProduct = new Product();
        mockProduct.setIdProduct(1L);
        mockProduct.setTitle("Product name");

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(mockProduct));

        Product retrievedProduct = productServiceImpl.retrieveProduct(1L);

        verify(productRepository).findById(1L);

        assertEquals(mockProduct, retrievedProduct);
    }

    @Test
    void deleteProduct() {
        Product mockProduct = new Product();
        mockProduct.setIdProduct(1L);
        mockProduct.setTitle("Product name");

        productServiceImpl.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void retrieveAllProducts() {
        Product mockProduct1 = new Product();
        mockProduct1.setIdProduct(1L);
        mockProduct1.setTitle("Product name 1");

        Product mockProduct2 = new Product();
        mockProduct2.setIdProduct(2L);
        mockProduct2.setTitle("Product name 2");

        List<Product> mockProducts = new java.util.ArrayList<Product>();
        mockProducts.add(mockProduct1);
        mockProducts.add(mockProduct2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> retrievedProducts = productServiceImpl.retreiveAllProduct();

        verify(productRepository).findAll();

        assertEquals(2, retrievedProducts.size());
        assertEquals(mockProduct1, retrievedProducts.get(0));
        assertEquals(mockProduct2, retrievedProducts.get(1));
    }

    @Test
    void retrieveAllProductsByCategory(){
        Product mockProduct1 = new Product();
        mockProduct1.setIdProduct(1L);
        mockProduct1.setTitle("Product name 1");
        mockProduct1.setCategory(ProductCategory.BOOKS);

        Product mockProduct2 = new Product();
        mockProduct2.setIdProduct(2L);
        mockProduct2.setTitle("Product name 2");
        mockProduct2.setCategory(ProductCategory.BOOKS);

        List<Product> mockProducts = new java.util.ArrayList<Product>();
        mockProducts.add(mockProduct1);
        mockProducts.add(mockProduct2);

        when(productRepository.findByCategory(ProductCategory.BOOKS)).thenReturn(mockProducts);

        List<Product> retrievedProducts = productServiceImpl.retrieveProductByCategory(ProductCategory.BOOKS);

        verify(productRepository).findByCategory(ProductCategory.BOOKS);

        assertEquals(2, retrievedProducts.size());
        assertEquals(mockProduct1, retrievedProducts.get(0));
        assertEquals(mockProduct2, retrievedProducts.get(1));
    }

    @Test
    void retreiveProductsByStock(){
        Product mockProduct1 = new Product();
        mockProduct1.setIdProduct(1L);
        mockProduct1.setTitle("Product name 1");

        Product mockProduct2 = new Product();
        mockProduct2.setIdProduct(2L);
        mockProduct2.setTitle("Product name 2");

        List<Product> mockProducts = new java.util.ArrayList<Product>();
        mockProducts.add(mockProduct1);
        mockProducts.add(mockProduct2);

        when(productRepository.findByStockIdStock(1L)).thenReturn(mockProducts);

        List<Product> retrievedProducts = productServiceImpl.retreiveProductStock(1L);

        verify(productRepository).findByStockIdStock(1L);

        assertEquals(2, retrievedProducts.size());
        assertEquals(mockProduct1, retrievedProducts.get(0));
        assertEquals(mockProduct2, retrievedProducts.get(1));
    }

}