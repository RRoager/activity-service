package com.clubapp.activityservice.service;

import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Get all activities from repository
     * @return list containing all activities
     */
    @Override
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    /**
     * Get Activity based on Activity name
     * @param name Activity name
     * @return Optional Activity
     */
    @Override
    public Optional<Activity> getActivitiesByName(String name) {
        return Optional.ofNullable(activityRepository.findByName(name));
    }

    /**
     * Get Activity based on Activity id
     * @param id Activity id
     * @return Optional Activity
     */
    @Override
    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    /**
     * Get all activities of a specific type
     * @param type Activity type
     * @return Optional list of activities
     */
    @Override
    public Optional<List<Activity>> getActivitiesByType(String type) {
        return Optional.ofNullable(activityRepository.findAllByType(type));
    }

    /**
     * Save new Activity to database if name is not present already
     * @param Activity Activity object
     * @return Activity
     */
    @Override
    public Activity saveActivity(Activity Activity) {
        if (getActivitiesByName(Activity.getName()).isPresent())
            return null;

        return activityRepository.save(Activity);
    }

    /**
     * Get Activity by id and update the Activity in database
     * @param id Activity id
     * @param updatedActivity Activity object
     * @return Activity
     */
    @Override
    public Activity updateActivity(Long id, Activity updatedActivity) {
        // compare and set all changes on first object
        return getActivityById(id)
                .map(oldActivity -> activityRepository.save(Activity.builder()
                        .id(id)
                        .name(updatedActivity.getName() != null ? updatedActivity.getName() : oldActivity.getName())
                        .type(updatedActivity.getType() != null ? updatedActivity.getType() : oldActivity.getType())
                        .build()))
                .orElse(null);
    }

    /**
     * Delete Activity based on id
     * @param id Activity id
     * @return boolean
     */
    @Override
    public boolean deleteActivity(Long id) {
        Activity activityToDelete = activityRepository.findById(id).orElse(null);

        if (activityToDelete == null) {
            return false;
        } else {
            activityRepository.delete(activityToDelete);
            return true;
        }
    }
}
