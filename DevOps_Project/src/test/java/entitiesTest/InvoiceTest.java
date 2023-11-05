package entitiesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.entities.Supplier;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class InvoiceTest {

    @InjectMocks
    private Invoice invoice;

    @Mock
    private Set<InvoiceDetail> invoiceDetails;

    @Mock
    private Supplier supplier;
    @Test
    public void testNoArgsConstructor() {
        Invoice invoice = new Invoice();
        assertNotNull(invoice);
        assertNull(invoice.getIdInvoice());
        assertEquals(0.0f, invoice.getAmountDiscount());
        assertEquals(0.0f, invoice.getAmountInvoice());
        assertNull(invoice.getDateCreationInvoice());
        assertNull(invoice.getDateLastModificationInvoice());
        assertNull(invoice.getArchived());
        assertNull(invoice.getInvoiceDetails());
        assertNull(invoice.getSupplier());
    }

    @Test
    public void testAllArgsConstructor() {
        Date creationDate = new Date();
        Date modificationDate = new Date();
        Invoice invoice = new Invoice(1L, 10.0f, 100.0f, creationDate, modificationDate, true, invoiceDetails, supplier);
        assertEquals(1L, invoice.getIdInvoice());
        assertEquals(10.0f, invoice.getAmountDiscount());
        assertEquals(100.0f, invoice.getAmountInvoice());
        assertEquals(creationDate, invoice.getDateCreationInvoice());
        assertEquals(modificationDate, invoice.getDateLastModificationInvoice());
        assertEquals(true, invoice.getArchived());
        assertEquals(invoiceDetails, invoice.getInvoiceDetails());
        assertEquals(supplier, invoice.getSupplier());
    }

    @Test
    public void testIdInvoiceGetterAndSetter() {
        invoice.setIdInvoice(1L);
        assertEquals(1L, invoice.getIdInvoice());
    }

    @Test
    public void testAmountDiscountGetterAndSetter() {
        invoice.setAmountDiscount(10.0f);
        assertEquals(10.0f, invoice.getAmountDiscount());
    }

    @Test
    public void testAmountInvoiceGetterAndSetter() {
        invoice.setAmountInvoice(100.0f);
        assertEquals(100.0f, invoice.getAmountInvoice());
    }

    @Test
    public void testDateCreationInvoiceGetterAndSetter() {
        Date creationDate = new Date();
        invoice.setDateCreationInvoice(creationDate);
        assertEquals(creationDate, invoice.getDateCreationInvoice());
    }

    @Test
    public void testDateLastModificationInvoiceGetterAndSetter() {
        Date modificationDate = new Date();
        invoice.setDateLastModificationInvoice(modificationDate);
        assertEquals(modificationDate, invoice.getDateLastModificationInvoice());
    }

    @Test
    public void testArchivedGetterAndSetter() {
        invoice.setArchived(true);
        assertEquals(true, invoice.getArchived());
    }

    @Test
    public void testInvoiceDetailsGetterAndSetter() {
        Set<InvoiceDetail> testInvoiceDetails = new HashSet<>();
        InvoiceDetail invoiceDetail1 = new InvoiceDetail();
        InvoiceDetail invoiceDetail2 = new InvoiceDetail();
        testInvoiceDetails.add(invoiceDetail1);
        testInvoiceDetails.add(invoiceDetail2);

        invoice.setInvoiceDetails(testInvoiceDetails);
        assertEquals(testInvoiceDetails, invoice.getInvoiceDetails());
    }

    @Test
    public void testSupplierGetterAndSetter() {
        invoice.setSupplier(supplier);
        assertEquals(supplier, invoice.getSupplier());
    }
}

