package com.clubapp.activityservice.service;


import com.clubapp.activityservice.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<Activity> getActivities();

    Optional<Activity> getActivitiesByName(String name);

    Optional<Activity> getActivityById(Long id);

    Optional<List<Activity>> getActivitiesByType(String type);

    Activity saveActivity(Activity Activity);

    Activity updateActivity(Long id, Activity Activity);

    boolean deleteActivity(Long id);
}
