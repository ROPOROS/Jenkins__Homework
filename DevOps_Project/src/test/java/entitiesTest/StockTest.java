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
import tn.esprit.devops_project.entities.Stock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class StockTest {

    @InjectMocks
    private Stock stock;

    @Mock
    private Set<Product> products;

    @Test
    public void testNoArgsConstructor() {
        Stock stock = new Stock();
        assertNull(stock.getTitle());
        assertNull(stock.getProducts());
    }

    @Test
    public void testAllArgsConstructor() {
        Stock stock = new Stock(1L, "Test Stock", products);
        assertEquals(1L, stock.getIdStock());
        assertEquals("Test Stock", stock.getTitle());
        assertEquals(products, stock.getProducts());
    }

    @Test
    public void testIdStockGetterAndSetter() {
        stock.setIdStock(1L);
        assertEquals(1L, stock.getIdStock());
    }

    @Test
    public void testTitleGetterAndSetter() {
        stock.setTitle("Test Stock");
        assertEquals("Test Stock", stock.getTitle());
    }

    @Test
    public void testProductsGetterAndSetter() {
        Set<Product> testProducts = new HashSet<>();
        Product product1 = new Product();
        Product product2 = new Product();
        testProducts.add(product1);
        testProducts.add(product2);

        stock.setProducts(testProducts);
        assertEquals(testProducts, stock.getProducts());
    }
}

