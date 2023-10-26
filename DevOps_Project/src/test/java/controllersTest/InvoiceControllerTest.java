package controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.controllers.InvoiceController;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    @Mock
    IInvoiceService invoiceService;

    @InjectMocks
    InvoiceController invoiceController;

    @Test
    public void getInvoicesTest() {
        when(invoiceService.retrieveAllInvoices()).thenReturn(Arrays.asList(
                new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null),
                new Invoice(2L, 20.0f, 200.0f, new Date(), new Date(), false, null, null)
        ));

        List<Invoice> invoices = invoiceController.getInvoices();

        assertEquals(2, invoices.size());
    }

    @Test
    public void retrieveInvoiceTest() {
        when(invoiceService.retrieveInvoice(1L)).thenReturn(new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null));

        Invoice invoice = invoiceController.retrieveInvoice(1L);

        assertNotNull(invoice);
        assertEquals(1L, invoice.getIdInvoice());
        assertEquals(10.0f, invoice.getAmountDiscount());
        assertEquals(100.0f, invoice.getAmountInvoice());
    }

    @Test
    public void cancelInvoiceTest() {
        invoiceController.cancelInvoice(1L);
        verify(invoiceService).cancelInvoice(1L);
    }

    @Test
    public void getInvoicesBySupplierTest() {
        when(invoiceService.getInvoicesBySupplier(1L)).thenReturn(Arrays.asList(
                new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null),
                new Invoice(2L, 20.0f, 200.0f, new Date(), new Date(), false, null, null)
        ));

        List<Invoice> invoiceList = invoiceController.getInvoicesBySupplier(1L);

        assertNotNull(invoiceList);
        assertEquals(2, invoiceList.size());
    }

    @Test
    public void assignOperatorToInvoiceTest() {
        invoiceController.assignOperatorToInvoice(1L, 1L);
        verify(invoiceService).assignOperatorToInvoice(1L, 1L);
    }

    @Test
    public void getTotalAmountInvoiceBetweenDatesTest() {
        when(invoiceService.getTotalAmountInvoiceBetweenDates(Mockito.any(), Mockito.any())).thenReturn(100.0f);

        float totalAmount = invoiceController.getTotalAmountInvoiceBetweenDates(new Date(), new Date());

        assertEquals(100.0f, totalAmount);
    }
}

