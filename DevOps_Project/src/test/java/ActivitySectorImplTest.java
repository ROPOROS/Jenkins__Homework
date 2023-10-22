import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.services.ActivitySectorImpl;

@ExtendWith(MockitoExtension.class)
public class ActivitySectorImplTest {
    @Mock
    ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    ActivitySectorImpl activitySectorService;

    @Test
    public void retrieveAllActivitySectorsTest() {
        when(activitySectorRepository.findAll()).thenReturn(Stream.of(
                        new ActivitySector(1L, "Code1", "Label1", null),
                        new ActivitySector(2L, "Code2", "Label2", null),
                        new ActivitySector(3L, "Code3", "Label3", null))
                .collect(Collectors.toList()));

        List<ActivitySector> activitySectors = activitySectorService.retrieveAllActivitySectors();

        assertEquals(3, activitySectors.size());
    }

    @Test
    public void addActivitySectorTest() {
        ActivitySector activitySector = new ActivitySector(1L, "Code1", "Label1", null);
        when(activitySectorRepository.save(activitySector)).thenReturn(activitySector);
        ActivitySector addedActivitySector = activitySectorService.addActivitySector(activitySector);

        assertNotNull(addedActivitySector);
        assertEquals("Code1", addedActivitySector.getCodeSecteurActivite());
    }

    @Test
    public void retrieveActivitySectorTest() {
        ActivitySector activitySector = new ActivitySector(1L, "Code1", "Label1", null);
        when(activitySectorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(activitySector));
        ActivitySector retrievedActivitySector = activitySectorService.retrieveActivitySector(1L);

        assertNotNull(retrievedActivitySector);
        assertEquals("Code1", retrievedActivitySector.getCodeSecteurActivite());
    }

    @Test
    public void deleteActivitySectorTest() {
        activitySectorService.deleteActivitySector(1L);
        verify(activitySectorRepository).deleteById(1L);
    }

    @Test
    public void updateActivitySectorTest() {
        ActivitySector activitySector = new ActivitySector(1L, "Code1", "Label1", null);
        Mockito.when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(activitySector);
        activitySector.setLibelleSecteurActivite("Label15");
        ActivitySector updatedActivitySector = activitySectorService.updateActivitySector(activitySector);

        assertNotNull(updatedActivitySector);
        assertEquals("Label15", updatedActivitySector.getLibelleSecteurActivite());
    }
}
