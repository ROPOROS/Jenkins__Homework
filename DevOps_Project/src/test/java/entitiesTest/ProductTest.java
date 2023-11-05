package entitiesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @InjectMocks
    private Product product;

    @Mock
    private Stock stock;

    @Test
    public void testNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
        assertNull(product.getIdProduct());
        assertNull(product.getTitle());
        assertEquals(0.0, product.getPrice());
        assertEquals(0, product.getQuantity());
        assertNull(product.getCategory());
        assertNull(product.getStock());
    }

    @Test
    public void testAllArgsConstructor() {

        Product product = new Product(1L, "Test Product", 10.99f, 5, ProductCategory.BOOKS, stock);
        assertEquals(1L, product.getIdProduct());
        assertEquals("Test Product", product.getTitle());
        assertEquals(10.99f, product.getPrice(), 0.01); // Using an epsilon for float comparison
        assertEquals(5, product.getQuantity());
        assertEquals(ProductCategory.BOOKS, product.getCategory());
        assertEquals(stock, product.getStock());
    }

    @Test
    public void testIdProductGetterAndSetter() {
        product.setIdProduct(1L);
        assertEquals(1L, product.getIdProduct());
    }

    @Test
    public void testTitleGetterAndSetter() {
        product.setTitle("Test Product");
        assertEquals("Test Product", product.getTitle());
    }

    @Test
    public void testPriceGetterAndSetter() {
        product.setPrice(10.99f);
        assertEquals(10.99f, product.getPrice(), 0.01); // Using an epsilon for float comparison
    }

    @Test
    public void testQuantityGetterAndSetter() {
        product.setQuantity(5);
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testCategoryGetterAndSetter() {
        ProductCategory category = ProductCategory.BOOKS;
        product.setCategory(category);
        assertEquals(category, product.getCategory());
    }

    @Test
    public void testStockGetterAndSetter() {
        product.setStock(stock);
        assertEquals(stock, product.getStock());
    }
}

