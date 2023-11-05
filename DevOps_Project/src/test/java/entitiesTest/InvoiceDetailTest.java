package entitiesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
@ExtendWith(MockitoExtension.class)
public class InvoiceDetailTest {

    @InjectMocks
    private InvoiceDetail invoiceDetail;

    @Mock
    private Product product;

    @Mock
    private Invoice invoice;

    @Test
    public void testNoArgsConstructor() {
        InvoiceDetail invoiceDetail  = new InvoiceDetail();
        assertNotNull(invoiceDetail);
        assertNull(invoiceDetail.getIdInvoiceDetail());
        assertEquals(0, invoiceDetail.getQuantity());
        assertEquals(0.0f, invoiceDetail.getPrice());
        assertNull(invoiceDetail.getProduct());
        assertNull(invoiceDetail.getInvoice());
    }

    @Test
    public void testAllArgsConstructor() {
        InvoiceDetail invoiceDetail = new InvoiceDetail(1L, 5, 10.99f, product, invoice);
        assertEquals(1L, invoiceDetail.getIdInvoiceDetail());
        assertEquals(5, invoiceDetail.getQuantity());
        assertEquals(10.99f, invoiceDetail.getPrice(), 0.01); // Using an epsilon for float comparison
        assertEquals(product, invoiceDetail.getProduct());
        assertEquals(invoice, invoiceDetail.getInvoice());
    }

    @Test
    public void testIdInvoiceDetailGetterAndSetter() {
        invoiceDetail.setIdInvoiceDetail(1L);
        assertEquals(1L, invoiceDetail.getIdInvoiceDetail());
    }

    @Test
    public void testQuantityGetterAndSetter() {
        invoiceDetail.setQuantity(5);
        assertEquals(5, invoiceDetail.getQuantity());
    }

    @Test
    public void testPriceGetterAndSetter() {
        invoiceDetail.setPrice(10.99f);
        assertEquals(10.99f, invoiceDetail.getPrice(), 0.01); // Using an epsilon for float comparison
    }

    @Test
    public void testProductGetterAndSetter() {
        invoiceDetail.setProduct(product);
        assertEquals(product, invoiceDetail.getProduct());
    }

    @Test
    public void testInvoiceGetterAndSetter() {
        invoiceDetail.setInvoice(invoice);
        assertEquals(invoice, invoiceDetail.getInvoice());
    }
}

