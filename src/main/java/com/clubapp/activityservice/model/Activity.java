package com.clubapp.activityservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    @OneToMany
    private List<ActivityLinks> activityLinks;
    @OneToMany
    private List<ActivityPrices> activityPrices;
//    private Schedule schedule;
//    private UserTeam userTeam;
}
