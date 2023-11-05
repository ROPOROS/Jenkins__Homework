package controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.controllers.ProductController;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.services.Iservices.IProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    IProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    public void addProductTest() {
        Product product = new Product(1L, "Product1", 10.0f, 33, ProductCategory.ELECTRONICS, null);

        when(productService.addProduct(product, 1L)).thenReturn(product);

        Product result = productController.addProduct(product, 1L);

        assertEquals("Product1", result.getTitle());
    }

    @Test
    public void retrieveProductTest() {
        Product product = new Product(1L, "Product1", 10.0f, 33, ProductCategory.ELECTRONICS, null);
        when(productService.retrieveProduct(1L)).thenReturn(product);

        Product result = productController.retrieveProduct(1L);

        assertEquals("Product1", result.getTitle());
    }

    @Test
    public void retrieveAllProductTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product1", 10.0f, 33, ProductCategory.ELECTRONICS, null));
        products.add(new Product(2L, "Product2", 20.0f, 25, ProductCategory.CLOTHING, null));

        when(productService.retreiveAllProduct()).thenReturn(products);

        List<Product> result = productController.retreiveAllProduct();
        assertEquals(2, result.size());
    }

    @Test
    public void retreiveProductStockTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product1", 10.0f, 33, ProductCategory.ELECTRONICS, null));
        products.add(new Product(2L, "Product2", 20.0f, 25, ProductCategory.CLOTHING, null));

        when(productService.retreiveProductStock(1L)).thenReturn(products);

        List<Product> result = productController.retreiveProductStock(1L);

        assertEquals(2, result.size());
    }

    @Test
    public void retrieveProductByCategoryTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product1", 10.0f, 33, ProductCategory.ELECTRONICS, null));
        products.add(new Product(2L, "Product2", 20.0f, 25, ProductCategory.ELECTRONICS, null));

        when(productService.retrieveProductByCategory(ProductCategory.ELECTRONICS)).thenReturn(products);

        List<Product> result = productController.retrieveProductByCategory(ProductCategory.ELECTRONICS);

        assertEquals(2, result.size());
    }

    @Test
    public void deleteProductTest() {
        productController.deleteProduct(1L);
        verify(productService).deleteProduct(1L);
    }
}

