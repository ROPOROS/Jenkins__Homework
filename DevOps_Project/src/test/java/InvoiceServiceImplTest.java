import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
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

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    public void retrieveAllInvoicesTest() {
        when(invoiceRepository.findAll()).thenReturn(Stream.of(
                        new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null),
                        new Invoice(2L, 20.0f, 200.0f, new Date(), new Date(), false, null, null),
                        new Invoice(3L, 30.0f, 300.0f, new Date(), new Date(), false, null, null))
                .collect(Collectors.toList()));

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
    public void retrieveInvoiceTest() {
        Invoice invoice = new Invoice(1L, 10.0f, 100.0f, new Date(), new Date(), false, null, null);
        when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(invoice));

        Invoice retrievedInvoice = invoiceService.retrieveInvoice(1L);

        assertNotNull(retrievedInvoice);
        assertEquals(10.0f, retrievedInvoice.getAmountDiscount());
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