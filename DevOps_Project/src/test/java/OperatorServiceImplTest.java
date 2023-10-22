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
                        new Operator((long)1,"Rain","Do","Niar", null),
                        new Operator((long)2,"Rain","Sometimes","Niar", null),
                        new Operator((long)3,"Rain","Codes","Niar", null))
                .collect(Collectors.toList()));
        assertEquals(3,operateurService.retrieveAllOperators().size());

    }

    @Test
    public void addOperateurTest() {
        Operator op = new Operator((long) 1, "NiAr", "ForFun", "MoreComplexPassword", null);
        when(operateurRepository.save(op)).thenReturn(op);
        assertEquals(op, operateurService.addOperator(op));
    }

    @Test
    public void retreiveOperateurTest() {
        Operator op = new Operator((long) 2, "NiAr", "ForFun", "MoreComplexPassword", null);
        when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op));
        Operator op1 = operateurService.retrieveOperator((long) 2);
        Assertions.assertNotNull(op1);

    }

    @Test
    public void deleteOperateurTest() {
        Operator op = new Operator((long) 1, "NiAr", "ForFun", "MoreComplexPassword", null);
        operateurService.deleteOperator((long) 1);
        verify(operateurRepository).deleteById((long) 1);

    }

    @Test
    public void updatetOperateurTest() {
        Operator op = new Operator((long)1,"NiAr","ForFun","MoreComplexPassword", null) ;
        Mockito.when(operateurRepository.save(Mockito.any(Operator.class))).thenReturn(op);
        op.setFname("mohamed");;
        Operator exisitingOp= operateurService.updateOperator(op) ;

        assertNotNull(exisitingOp);
        assertEquals("mohamed", op.getFname());
    }

}
