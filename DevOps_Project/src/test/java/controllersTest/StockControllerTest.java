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
        Stock stock = new Stock(1L, "Milk", null);

        when(stockService.addStock(stock)).thenReturn(stock);

        Stock result = stockController.addStock(stock);

        assertEquals("Milk", result.getTitle());
    }

    @Test
    public void retrieveStockTest() {
        Stock stock = new Stock(1L, "Milk", null);
        when(stockService.retrieveStock(1L)).thenReturn(stock);

        Stock result = stockController.retrieveStock(1L);

        assertEquals("Milk", result.getTitle());
    }

    @Test
    public void retrieveAllStockTest() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1L, "Milk", null));
        stocks.add(new Stock(2L, "Boxes", null));

        when(stockService.retrieveAllStock()).thenReturn(stocks);

        List<Stock> retrievedStocks = stockController.retrieveAllStock();

        assertEquals(2, retrievedStocks.size());
    }
}

