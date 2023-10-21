package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.ActivitySector;

import java.util.List;

public interface IActivitySector {
    List<ActivitySector> retrieveAllActivitySectors();

    ActivitySector addActivitySector(ActivitySector activitySector);

    void deleteActivitySector(Long id);

    ActivitySector updateActivitySector(ActivitySector activitySector);

    ActivitySector retrieveActivitySector(Long id);
}
