package com.clubapp.activityservice.service;

import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.model.Type;
import com.clubapp.activityservice.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final List<Type> teams = Arrays.asList(Type.ACROBATICS, Type.FITNESS, Type.PERFORMANCE, Type.TEAM_GYM);


    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Get all activities
     * @return list containing all activities
     */
    @Override
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    /**
     * Get all team activities
     * @return Optional list of activities
     */
    @Override
    public Optional<List<Activity>> getTeams() {
        return Optional.ofNullable(activityRepository.findByTypeIn(teams));
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
        if (validType(type)) {
            return Optional.ofNullable(activityRepository.findAllByType(Type.valueOf(type.toUpperCase().trim())));
        }
        return Optional.empty();
    }

    private static boolean validType(String type) {
        return Stream.of(Type.values())
                .map(Type::name)
                .toList()
                .contains(type.toUpperCase().trim());
    }

    /**
     * Save new Activity to database
     * @param activity Activity object
     * @return Activity
     */
    @Override
    public Activity saveActivity(Activity activity) {
        if (allParamsPresent(activity)) {
            return activityRepository.save(activity);
        }
        return null;
    }

    private static boolean allParamsPresent(Activity activity) {
        return Optional.ofNullable(activity.getName()).isPresent() &&
                Optional.ofNullable(activity.getDescription()).isPresent() &&
                Optional.ofNullable(activity.getType()).isPresent();
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
