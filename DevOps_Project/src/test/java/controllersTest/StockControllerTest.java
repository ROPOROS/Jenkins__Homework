package controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.controllers.StockController;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @Mock
    IStockService stockService;

    @InjectMocks
    StockController stockController;

    @Test
    public void addStockTest() {
        // Create a new stock for testing
        Stock stock = new Stock(1L, "Milk", null);

        // Mock the behavior to return the added stock
        when(stockService.addStock(stock)).thenReturn(stock);

        // Call the controller method
        Stock result = stockController.addStock(stock);

        // Verify the results
        assertEquals("Milk", result.getTitle());
    }

    @Test
    public void retrieveStockTest() {
        // Mock the behavior to return a stock
        Stock stock = new Stock(1L, "Milk", null);
        when(stockService.retrieveStock(1L)).thenReturn(stock);

        // Call the controller method
        Stock result = stockController.retrieveStock(1L);

        // Verify the results
        assertEquals("Milk", result.getTitle());
    }

    @Test
    public void retrieveAllStockTest() {
        // Create a list of stocks for testing
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1L, "Milk", null));
        stocks.add(new Stock(2L, "Boxes", null));

        // Mock the behavior to return the list of stocks
        when(stockService.retrieveAllStock()).thenReturn(stocks);

        // Call the controller method
        List<Stock> retrievedStocks = stockController.retrieveAllStock();

        // Verify the results
        assertEquals(2, retrievedStocks.size());
    }
}

