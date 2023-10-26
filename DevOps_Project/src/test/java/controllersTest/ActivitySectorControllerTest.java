package controllersTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.controllers.ActivitySectorController;
import tn.esprit.devops_project.controllers.OperatorController;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.Iservices.IActivitySector;
import tn.esprit.devops_project.services.Iservices.IOperatorService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ActivitySectorControllerTest {

    @InjectMocks
    private ActivitySectorController activitySectorController;

    @Mock
    private IActivitySector activitySectorService;

    @Test
    public void retrieveAllActivitySectorsTest() {
        // Mock the behavior to return a list of activity sectors
        when(activitySectorService.retrieveAllActivitySectors()).thenReturn(Arrays.asList(
                new ActivitySector(1L, "Code1", "Label1", null),
                new ActivitySector(2L, "Code2", "Label2", null)
        ));

        List<ActivitySector> activitySectors = activitySectorController.retrieveAllActivitySectors();

        assertEquals(2, activitySectors.size());
    }

    @Test
    public void addActivitySectorTest() {
        ActivitySector newActivitySector = new ActivitySector(1L, "Code1", "Label1", null);

        when(activitySectorService.addActivitySector(newActivitySector)).thenReturn(newActivitySector);

        ActivitySector addedActivitySector = activitySectorController.addActivitySector(newActivitySector);

        assertEquals(newActivitySector, addedActivitySector);
    }

    @Test
    public void deleteActivitySectorTest() {
        activitySectorController.deleteActivitySector(1L);
        verify(activitySectorService).deleteActivitySector(1L);
    }

    @Test
    public void updateActivitySectorTest() {
        ActivitySector activitySector = new ActivitySector(1L, "Code1", "Label1", null);

        when(activitySectorService.updateActivitySector(activitySector)).thenReturn(activitySector);

        ActivitySector updatedActivitySector = activitySectorController.updateActivitySector(activitySector);

        assertEquals(activitySector, updatedActivitySector);
    }

    @Test
    public void retrieveActivitySectorTest() {
        // Mock the behavior to return an activity sector
        when(activitySectorService.retrieveActivitySector(1L)).thenReturn(new ActivitySector(1L, "Code1", "Label1", null));

        ActivitySector activitySector = activitySectorController.retrieveActivitySector(1L);

        assertEquals(1L, activitySector.getIdSecteurActivite());
        assertEquals("Code1", activitySector.getCodeSecteurActivite());
        assertEquals("Label1", activitySector.getLibelleSecteurActivite());
    }
}


