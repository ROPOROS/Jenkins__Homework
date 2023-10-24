import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperatorServiceImplTest {


    @Mock
    OperatorRepository operateurRepository;

    @InjectMocks
    OperatorServiceImpl operateurService;

    @Test
    public void retrieveAllOperateursTest() {
        when(operateurRepository.findAll()).thenReturn(Stream.of(
                        new Operator(1L,"Rain","Do","Niar", null),
                        new Operator(2L,"Rain","Sometimes","Niar", null),
                        new Operator(3L,"Rain","Codes","Niar", null))
                .collect(Collectors.toList()));
        assertEquals(3,operateurService.retrieveAllOperators().size());

    }

    @Test
    public void addOperateurTest() {
        Operator op = new Operator(1L, "NiAr", "ForFun", "MoreComplexPassword", null);
        when(operateurRepository.save(op)).thenReturn(op);
        assertEquals(op, operateurService.addOperator(op));
    }

    @Test
    public void retreiveOperateurTest() {
        Operator op = new Operator(2L, "NiAr", "ForFun", "MoreComplexPassword", null);
        when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op));
        Operator op1 = operateurService.retrieveOperator(2L);
        Assertions.assertNotNull(op1);

    }

    @Test
    public void retrieveOperatorNotFoundTest() {
        when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NullPointerException.class, () -> {
            operateurService.retrieveOperator(2L);
        });
    }

    @Test
    public void deleteOperateurTest() {
        Operator op = new Operator(1L, "NiAr", "ForFun", "MoreComplexPassword", null);
        operateurService.deleteOperator(1L);
        verify(operateurRepository).deleteById(1L);

    }

    @Test
    public void updatetOperateurTest() {
        Operator op = new Operator(1L,"NiAr","ForFun","MoreComplexPassword", null) ;
        Mockito.when(operateurRepository.save(Mockito.any(Operator.class))).thenReturn(op);
        op.setFname("mohamed");;
        Operator exisitingOp= operateurService.updateOperator(op) ;

        assertNotNull(exisitingOp);
        assertEquals("mohamed", op.getFname());
    }

}
