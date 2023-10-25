import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplTest {
    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    //Test for retrieving all suppliers
    @Test
    public void retrieveAllSuppliersTest() {
        when(supplierRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Supplier(1L, "Code1", "label1", SupplierCategory.CONVENTIONNE, null, null),
                        new Supplier(2L, "Code2", "label2", SupplierCategory.ORDINAIRE, null, null),
                        new Supplier(3L, "Code3", "label3", SupplierCategory.ORDINAIRE, null, null)
                )
        );

        assertEquals(3, supplierService.retrieveAllSuppliers().size());
    }

    // Test for adding a supplier
    @Test
    public void addSupplierTest() {
        Supplier supplier = new Supplier(1L,"Code1","label1", SupplierCategory.CONVENTIONNE,null, null);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        assertEquals(supplier, supplierService.addSupplier(supplier));
    }

    // Test for supplier found
    @Test
    public void retreiveSupplierTest() {
        Supplier supplier = new Supplier(2L,"Code2","label2", SupplierCategory.ORDINAIRE,null, null);
        when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(supplier));
        Supplier supplier1 = supplierService.retrieveSupplier(2L);
        Assertions.assertNotNull(supplier1);

    }
    //Test for supplier not found
    @Test
    public void retrieveSupplierNotFoundTest() {
        when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            supplierService.retrieveSupplier(2L);
        });
    }

    //Test for deleting a supplier
    @Test
    public void deleteSupplierTest() {
        supplierService.deleteSupplier(1L);
        verify(supplierRepository).deleteById(1L);

    }
    //Test for updating a supplier
    @Test
    public void updateSupplierTest() {
        Supplier supplier = new Supplier(1L,"Code1","label1", SupplierCategory.CONVENTIONNE,null, null);
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);
        supplier.setCode("Code15");;
        Supplier exisitingSupplier= supplierService.updateSupplier(supplier) ;

        assertNotNull(exisitingSupplier);
        assertEquals("Code15", supplier.getCode());
    }
}
