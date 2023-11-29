package com.clubapp.activityservice.controller;

import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ActivityController {

    private final ActivityService activityService;

    ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivities() {
        return ResponseEntity.ok(activityService.getActivities());
    }

    @GetMapping("/activities/{type}")
    public ResponseEntity<List<Activity>> getActivitiesByType(@PathVariable String type) {
        return ResponseEntity.of(activityService.getActivitiesByType(type));
    }

    @GetMapping("/activity/{name}")
    public ResponseEntity<Activity> getActivitiesByName(@PathVariable String name) {
        return ResponseEntity.of(activityService.getActivitiesByName(name));
    }

    @PostMapping("/activity")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity Activity) {
        Activity newActivity = activityService.saveActivity(Activity);
        if (newActivity != null) {
            return ResponseEntity.ok(newActivity);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity Activity) {
        return ResponseEntity.ofNullable(activityService.updateActivity(id, Activity));
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
        if (activityService.deleteActivity(id)) {
            return ResponseEntity.ok("Deleted Activity with ID: " + id);
        }
        return ResponseEntity.notFound().build();
    }
}
