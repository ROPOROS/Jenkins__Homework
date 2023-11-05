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
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class SupplierTest {

    @InjectMocks
    private Supplier supplier;

    @Mock
    private Set<Invoice> invoices;

    @Test
    public void testNoArgsConstructor() {
        Supplier supplier = new Supplier();
        assertNull(supplier.getIdSupplier());
        assertNotNull(supplier);
        assertNull(supplier.getCode());
        assertNull(supplier.getLabel());
        assertNull(supplier.getSupplierCategory());
        assertNull(supplier.getInvoices());

    }

    @Test
    public void testAllArgsConstructor() {
        Supplier supplier = new Supplier(1L, "12345", "Test Supplier", SupplierCategory.CONVENTIONNE, invoices);
        assertEquals(1L, supplier.getIdSupplier());
        assertEquals("12345", supplier.getCode());
        assertEquals("Test Supplier", supplier.getLabel());
        assertEquals(SupplierCategory.CONVENTIONNE, supplier.getSupplierCategory()); // Use the enum value directly
        assertEquals(invoices, supplier.getInvoices());
    }


    @Test
    public void testIdSupplierGetterAndSetter() {
        supplier.setIdSupplier(1L);
        assertEquals(1L, supplier.getIdSupplier());
    }

    @Test
    public void testCodeGetterAndSetter() {
        supplier.setCode("12345");
        assertEquals("12345", supplier.getCode());
    }

    @Test
    public void testLabelGetterAndSetter() {
        supplier.setLabel("Test Supplier");
        assertEquals("Test Supplier", supplier.getLabel());
    }

    @Test
    public void testSupplierCategoryGetterAndSetter() {
        SupplierCategory expectedCategory = SupplierCategory.CONVENTIONNE;
        supplier.setSupplierCategory(expectedCategory);
        assertEquals(expectedCategory, supplier.getSupplierCategory());
    }


    @Test
    public void testInvoicesGetterAndSetter() {
        Set<Invoice> testInvoices = new HashSet<>();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        testInvoices.add(invoice1);
        testInvoices.add(invoice2);

        supplier.setInvoices(testInvoices);
        assertEquals(testInvoices, supplier.getInvoices());
    }
}
