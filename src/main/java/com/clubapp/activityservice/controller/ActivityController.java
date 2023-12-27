package com.clubapp.activityservice.controller;

import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.model.Type;
import com.clubapp.activityservice.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @GetMapping("/")
    public ResponseEntity<List<Activity>> getActivities() {
        return ResponseEntity.ok(activityService.getActivities());
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Activity>> getTeams() {
        return ResponseEntity.of(activityService.getTeams());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        return ResponseEntity.of(activityService.getActivityById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Activity>> getActivitiesByType(@PathVariable String type) {
        return ResponseEntity.of(activityService.getActivitiesByType(type));
    }

    @PostMapping("/")
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        Activity newActivity = activityService.saveActivity(activity);
        if (newActivity != null) {
            return ResponseEntity.ok(newActivity);
        }
        return new ResponseEntity<>("Activity must have a name, description and type", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return ResponseEntity.ofNullable(activityService.updateActivity(id, activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
        if (activityService.deleteActivity(id)) {
            return ResponseEntity.ok("Deleted Activity with ID: " + id);
        }
        return ResponseEntity.notFound().build();
    }
}
