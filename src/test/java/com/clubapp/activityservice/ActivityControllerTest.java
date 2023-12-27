package com.clubapp.activityservice;

import com.clubapp.activityservice.controller.ActivityController;
import com.clubapp.activityservice.model.Activity;
import com.clubapp.activityservice.model.Link;
import com.clubapp.activityservice.model.Price;
import com.clubapp.activityservice.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.clubapp.activityservice.model.Type.ACROBATICS;
import static com.clubapp.activityservice.model.Type.EVENT;
import static com.clubapp.activityservice.model.Type.FITNESS;
import static com.clubapp.activityservice.model.Type.MEETING;
import static com.clubapp.activityservice.model.Type.TEAM_GYM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ActivityControllerTest {

    @InjectMocks
    ActivityController activityController;

    @Mock
    ActivityService activityService;
    private final List<Link> links = Arrays.asList(
            new Link(1L, Instant.now(), Instant.now(), "Buy", "http://buy.com/1", new Activity()),
            new Link(1L, Instant.now(), Instant.now(), "Buy", "http://buy.com/1", new Activity()),
            new Link(2L, Instant.now(), Instant.now(), "Sign Up", "http://signup.com/1", new Activity()),
            new Link(3L, Instant.now(), Instant.now(), "Buy", "http://buy.com/2", new Activity()),
            new Link(4L, Instant.now(), Instant.now(), "Sign Up", "http://signup.com/2", new Activity()),
            new Link(6L, Instant.now(), Instant.now(), "Sign Up", "http://signup.com/3", new Activity()),
            new Link(7L, Instant.now(), Instant.now(), "Buy", "http://buy.com/4", new Activity()),
            new Link(8L, Instant.now(), Instant.now(), "Sign Up", "http://signup.com/4", new Activity()),
            new Link(9L, Instant.now(), Instant.now(), "Buy", "http://buy.com/5", new Activity()),
            new Link(10L, Instant.now(), Instant.now(), "Sign Up", "http://signup.com/5", new Activity())
    );
    private final List<Price> prices = Arrays.asList(
            new Price(1L, Instant.now(), Instant.now(), "One Day", 50.0, new Activity()),
            new Price(2L, Instant.now(), Instant.now(), "Two Days", 90.0, new Activity()),
            new Price(3L, Instant.now(), Instant.now(), "One Day", 50.0, new Activity()),
            new Price(4L, Instant.now(), Instant.now(), "Two Days", 90.0, new Activity()),
            new Price(7L, Instant.now(), Instant.now(), "One Day", 50.0, new Activity()),
            new Price(8L, Instant.now(), Instant.now(), "Two Days", 90.0, new Activity()),
            new Price(9L, Instant.now(), Instant.now(), "One Day", 30.0, new Activity())
    );

    private final List<Activity> activities = Arrays.asList(
            new Activity(1L, Instant.now(), Instant.now(), "Jumping Jacks", "We jump a lot", ACROBATICS, links,
                    prices),
            new Activity(2L, Instant.now(), Instant.now(), "Heavy Weights", "We lift a lot", FITNESS, links,
                    prices),
            new Activity(3L, Instant.now(), Instant.now(), "Board Meeting", "Meeting for board members", MEETING, links,
                    prices),
            new Activity(4L, Instant.now(), Instant.now(), "Team Jump", "We jump a lot, together", TEAM_GYM, links,
                    prices),
            new Activity(5L, Instant.now(), Instant.now(), "Field Trip", "Field trip to Jylland", EVENT, links,
                    prices)
    );

    @BeforeEach
    public void setUp() {
        links.get(0).setActivity(activities.get(0));
        links.get(1).setActivity(activities.get(0));
        links.get(2).setActivity(activities.get(1));
        links.get(3).setActivity(activities.get(1));
        links.get(4).setActivity(activities.get(2));
        links.get(5).setActivity(activities.get(2));
        links.get(6).setActivity(activities.get(3));
        links.get(7).setActivity(activities.get(3));
        links.get(8).setActivity(activities.get(4));
        links.get(9).setActivity(activities.get(4));

        prices.get(0).setActivity(activities.get(0));
        prices.get(1).setActivity(activities.get(0));
        prices.get(2).setActivity(activities.get(1));
        prices.get(3).setActivity(activities.get(1));
        prices.get(4).setActivity(activities.get(3));
        prices.get(5).setActivity(activities.get(3));
        prices.get(6).setActivity(activities.get(4));
    }

    @Test
    public void shouldReturnMessage() throws Exception {
        when(activityService.getActivities()).thenReturn(activities);

        ResponseEntity<List<Activity>> result = activityController.getActivities();

        assertEquals(result.getBody().toArray().length, 5);
    }
}
