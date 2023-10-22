import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;
import tn.esprit.devops_project.services.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void addProductTest() {
        Stock stock = new Stock(1L, "Stock1", null); // You can create a stock for testing
        Product product = new Product(1L, "Product1", 10.0f, 5, ProductCategory.ELECTRONICS, stock);
        when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);
        Product addedProduct = productService.addProduct(product, 1L);

        assertNotNull(addedProduct);
        assertEquals("Product1", addedProduct.getTitle());
    }

    @Test
    public void retrieveProductTest() {
        Product product = new Product(1L, "Product1", 10.0f, 5, ProductCategory.ELECTRONICS, null);
        when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Product retrievedProduct = productService.retrieveProduct(1L);

        assertNotNull(retrievedProduct);
        assertEquals("Product1", retrievedProduct.getTitle());
    }

    @Test
    public void retrieveAllProductTest() {
        when(productRepository.findAll()).thenReturn(Stream.of(
                        new Product(1L, "Product1", 10.0f, 5, ProductCategory.ELECTRONICS, null),
                        new Product(2L, "Product2", 20.0f, 10, ProductCategory.CLOTHING, null),
                        new Product(3L, "Product3", 15.0f, 8, ProductCategory.BOOKS, null))
                .collect(Collectors.toList()));

        List<Product> productList = productService.retreiveAllProduct();

        assertEquals(3, productList.size());
    }

    @Test
    public void deleteProductTest() {
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    public void retrieveProductByCategoryTest() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        when(productRepository.findByCategory(category)).thenReturn(Stream.of(
                        new Product(1L, "Product1", 10.0f, 5, category, null),
                        new Product(2L, "Product2", 20.0f, 10, category, null))
                .collect(Collectors.toList()));

        List<Product> productList = productService.retrieveProductByCategory(category);

        assertEquals(2, productList.size());
    }

    @Test
    public void retrieveProductStockTest() {
        Stock stock = new Stock(1L, "Stock1", null);
        when(productRepository.findByStockIdStock(1L)).thenReturn(Stream.of(
                        new Product(1L, "Product1", 10.0f, 5, ProductCategory.ELECTRONICS, stock),
                        new Product(2L, "Product2", 20.0f, 10, ProductCategory.CLOTHING, stock))
                .collect(Collectors.toList()));

        List<Product> productList = productService.retreiveProductStock(1L);

        assertEquals(2, productList.size());
    }

}
