package tn.esprit.devops_project.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.List;

@RestController
@AllArgsConstructor
public class ActivitySectorController {

    IActivitySector activitySectorService;

    @GetMapping("/activitySector")
    public List<ActivitySector> retrieveAllActivitySectors(){
        return activitySectorService.retrieveAllActivitySectors();
    }

    @PostMapping("/activitySector")
    public ActivitySector addActivitySector(@RequestBody ActivitySector activitySector){
        return activitySectorService.addActivitySector(activitySector);
    }

    @DeleteMapping("/activitySector/{id}")
    public void deleteActivitySector(@PathVariable Long id){
        activitySectorService.deleteActivitySector(id);
    }

    @PutMapping("/activitySector")
    public ActivitySector updateActivitySector(@RequestBody ActivitySector activitySector){
        return activitySectorService.updateActivitySector(activitySector);
    }

    @GetMapping("/activitySector/{id}")
    public ActivitySector retrieveActivitySector(@PathVariable Long id){
        return activitySectorService.retrieveActivitySector(id);
    }
}
