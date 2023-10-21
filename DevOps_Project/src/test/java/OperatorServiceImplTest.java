
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.Iservices.IOperatorService;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator(1L, "John", "Doe", "Password", null)); // Adjust the constructor parameters
        operators.add(new Operator(2L, "Johnn", "Doee", "Passwordd", null)); // Adjust the constructor parameters

        when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> result = operatorService.retrieveAllOperators();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddOperator() {
        Operator operator = new Operator(1L, "John", "Doe", "Password", null); // Adjust the constructor parameters
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.addOperator(operator);

        assertEquals(operator, result);
    }

    @Test
    public void testDeleteOperator() {
        Long id = 1L;
        operatorService.deleteOperator(id);

        verify(operatorRepository).deleteById(id);
    }

    @Test
    public void testUpdateOperator() {
        Operator operator = new Operator(1L, "John", "Doe", "Password", null); // Adjust the constructor parameters
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.updateOperator(operator);

        assertEquals(operator, result);
    }

    @Test
    public void testRetrieveOperator() {
        Long id = 1L;
        Operator operator = new Operator(1L, "John", "Doe", "Password", null); // Adjust the constructor parameters
        when(operatorRepository.findById(id)).thenReturn(Optional.of(operator));

        Operator result = operatorService.retrieveOperator(id);

        assertEquals(operator, result);
    }
}
