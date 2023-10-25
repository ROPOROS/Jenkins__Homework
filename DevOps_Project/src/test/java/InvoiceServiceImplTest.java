import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;


import java.util.Arrays;
import java.util.Date;
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
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.*;
import tn.esprit.devops_project.services.ActivitySectorImpl;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceImplTest {
    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    OperatorRepository operatorRepository;

    @Mock
    SupplierRepository supplierRepository;

    @Mock
    InvoiceDetailRepository invoiceDetailRepository;

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    public void retrieveAllInvoicesTest() {
        when(invoiceRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null),
                        new Invoice(2L, 20.0f, 200.0f, new Date(), new Date(), false, null, null),
                        new Invoice(3L, 30.0f, 300.0f, new Date(), new Date(), false, null, null)
                )
        );

        List<Invoice> invoiceList = invoiceService.retrieveAllInvoices();

        assertEquals(3, invoiceList.size());
    }


    @Test
    public void cancelInvoiceTest() {
        Invoice invoice = new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null);
        when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(invoice));

        invoiceService.cancelInvoice(1L);

        verify(invoiceRepository).save(invoice);
    }

    @Test
    public void cancelInvoiceNotFoundTest() {
        // Simulate that the invoice is not found
        when(invoiceRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the cancelInvoice method for an invoice that is not found
        Assertions.assertThrows(NullPointerException.class, () -> {
            invoiceService.cancelInvoice(1L);
        });

        // Verify that the find method was called
        verify(invoiceRepository).findById(1L);

        // Additional assertion: Ensure that no invoices were archived
        // You might need to adjust this based on your data setup
    }

    /*@Test
    public void cancelInvoiceWithJPQLTest() {
        // Create an invoice that we expect to be canceled using JPQL
        Invoice invoice = new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null);

        when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(invoice)); // Simulate finding the invoice
        doNothing().when(invoiceRepository).updateInvoice(Mockito.anyLong()); // Mock the JPQL update method

        // Call the cancelInvoice method
        invoiceService.cancelInvoice(1L);

        // Verify that the find method was called and the save method was never called
        verify(invoiceRepository).findById(1L);
        verify(invoiceRepository, never()).save(invoice);
        verify(invoiceRepository).updateInvoice(1L); // Verify that the JPQL update method was called
    }*/



    @Test
    public void retrieveInvoiceTest() {
        Invoice invoice = new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null);
        when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(invoice));

        Invoice retrievedInvoice = invoiceService.retrieveInvoice(1L);

        assertNotNull(retrievedInvoice);
        assertEquals(10.0f, retrievedInvoice.getAmountDiscount());
    }

    @Test
    public void retrieveInvoiceNotFoundTest() {
        when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty()); // Simulate not finding the invoice

        Assertions.assertThrows(NullPointerException.class, () -> {
            invoiceService.retrieveInvoice(1L);
        });
    }



    @Test
    public void getTotalAmountInvoiceBetweenDatesTest() {
        Date startDate = new Date(1634994000000L); // October 23, 2021
        Date endDate = new Date(1635354000000L);   // October 27, 2021
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(500.0f);

        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        assertEquals(500.0f, totalAmount);
    }


}