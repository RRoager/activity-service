package com.clubapp.activityservice.repository;

import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByType(Type type);

    List<Activity> findByTypeIn(List<Type> teams);
}
