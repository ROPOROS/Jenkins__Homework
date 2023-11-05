package entitiesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Invoice;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class OperatorTest {

    @InjectMocks
    private Operator operator;

    @Mock
    private Set<Invoice> invoices;


    @Test
    public void testNoArgsConstructor() {
        Operator operator = new Operator();
        assertNotNull(operator);
        assertNull(operator.getIdOperateur());
        assertNull(operator.getFname());
        assertNull(operator.getLname());
        assertNull(operator.getPassword());
        assertNull(operator.getInvoices());
    }

    @Test
    public void testAllArgsConstructor() {
        Operator operator = new Operator(1L, "John", "Doe", "password123", invoices);
        assertEquals(1L, operator.getIdOperateur());
        assertEquals("John", operator.getFname());
        assertEquals("Doe", operator.getLname());
        assertEquals("password123", operator.getPassword());
        assertEquals(invoices, operator.getInvoices());
    }

    @Test
    public void testIdOperateurGetterAndSetter() {
        operator.setIdOperateur(1L);
        assertEquals(1L, operator.getIdOperateur());
    }

    @Test
    public void testFnameGetterAndSetter() {
        operator.setFname("John");
        assertEquals("John", operator.getFname());
    }

    @Test
    public void testLnameGetterAndSetter() {
        operator.setLname("Doe");
        assertEquals("Doe", operator.getLname());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        operator.setPassword("password123");
        assertEquals("password123", operator.getPassword());
    }

    @Test
    public void testInvoicesGetterAndSetter() {
        Set<Invoice> testInvoices = new HashSet<>();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        testInvoices.add(invoice1);
        testInvoices.add(invoice2);

        operator.setInvoices(testInvoices);
        assertEquals(testInvoices, operator.getInvoices());
    }
}

