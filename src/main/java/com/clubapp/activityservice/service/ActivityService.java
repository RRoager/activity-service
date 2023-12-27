package com.clubapp.activityservice.service;


import com.clubapp.activityservice.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<Activity> getActivities();

    Optional<Activity> getActivityById(Long id);

    Optional<List<Activity>> getActivitiesByType(String type);

    Activity saveActivity(Activity activity);

    Activity updateActivity(Long id, Activity activity);

    boolean deleteActivity(Long id);

    Optional<List<Activity>> getTeams();
}
