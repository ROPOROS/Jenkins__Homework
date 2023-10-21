package tn.esprit.devops_project.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivitySectorImpl implements IActivitySector {

    ActivitySectorRepository activitySectorRepository;
    @Override
    public List<ActivitySector> retrieveAllActivitySectors() {
        return activitySectorRepository.findAll();
    }

    @Override
    public ActivitySector addActivitySector(ActivitySector activitySector) {
        return activitySectorRepository.save(activitySector);
    }

    @Override
    public void deleteActivitySector(Long id) {
        activitySectorRepository.deleteById(id);
    }

    @Override
    public ActivitySector updateActivitySector(ActivitySector activitySector) {
        return activitySectorRepository.save(activitySector);
    }

    @Override
    public ActivitySector retrieveActivitySector(Long id) {
        return activitySectorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid activitySector Id:" + id));
    }
}
