package com.clubapp.activityservice.repository;

import com.clubapp.activityservice.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findByName(String name);

    List<Activity> findAllByType(String type);
}
