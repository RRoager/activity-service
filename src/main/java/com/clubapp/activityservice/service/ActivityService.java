package com.clubapp.activityservice.service;


import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.model.Type;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<Activity> getActivities();

    Optional<Activity> getActivityById(Long id);

    Optional<List<Activity>> getActivitiesByType(Type type);

    Activity saveActivity(Activity Activity);

    Activity updateActivity(Long id, Activity Activity);

    boolean deleteActivity(Long id);

    Optional<List<Activity>> getTeams();
}
