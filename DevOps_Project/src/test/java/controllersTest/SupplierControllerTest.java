package controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.controllers.SupplierController;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupplierControllerTest {

    @Mock
    ISupplierService supplierService;

    @InjectMocks
    SupplierController supplierController;

    @Test
    public void getSuppliersTest() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(1L,"Code1","label1", SupplierCategory.CONVENTIONNE,null, null));
        suppliers.add(new Supplier(2L,"Code2","label2", SupplierCategory.ORDINAIRE,null, null));

        when(supplierService.retrieveAllSuppliers()).thenReturn(suppliers);

        List<Supplier> retrievedSuppliers = supplierController.getSuppliers();

        assertEquals(2, retrievedSuppliers.size());
    }


    @Test
    public void retrieveSupplierTest() {
        Supplier supplier = new Supplier(1L,"Code1","label1", SupplierCategory.CONVENTIONNE,null, null);
        when(supplierService.retrieveSupplier(1L)).thenReturn(supplier);

        Supplier retrievedSupplier = supplierController.retrieveSupplier(1L);

        assertEquals("Code1", retrievedSupplier.getCode());
    }

    @Test
    public void addSupplierTest() {
        Supplier supplier = new Supplier(1L,"Code1","label1", SupplierCategory.CONVENTIONNE,null, null);

        when(supplierService.addSupplier(supplier)).thenReturn(supplier);

        Supplier addedSupplier = supplierController.addSupplier(supplier);

        assertEquals("Code1", addedSupplier.getCode());
    }

    @Test
    public void removeSupplierTest() {
        supplierController.removeFournisseur(1L);

        verify(supplierService).deleteSupplier(1L);
    }

    @Test
    public void modifySupplierTest() {
        Supplier supplier = new Supplier(1L,"Code1","label1", SupplierCategory.CONVENTIONNE,null, null);
        when(supplierService.updateSupplier(supplier)).thenReturn(supplier);

        Supplier modifiedSupplier = supplierController.modifyFournisseur(supplier);
        assertEquals("Code1", modifiedSupplier.getCode());
    }
}

