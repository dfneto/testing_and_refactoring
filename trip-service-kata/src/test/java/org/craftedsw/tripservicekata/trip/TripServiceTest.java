package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.builder.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User SOME_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip BRAZIL = new Trip();
    private static final Trip LONDON = new Trip();
    private User loggedInUser;
    private TripService tripService;

    @Before
    public void initialize(){
        tripService = new TestableTripService();
        loggedInUser = REGISTERED_USER;
    }

    @Test(expected = UserNotLoggedInException.class) public void
    should_validate_the_logged_in_user() {
        loggedInUser = GUEST;

        tripService.getTripsByUser(SOME_USER);

    }

    @Test public void
    should_not_return_any_trips_when_user_are_not_friends() {
        User stranger = aUser()
                                    .friendsWith(ANOTHER_USER)
                                    .withTripsTo(BRAZIL)
                                    .build();

        List<Trip> trips = tripService.getTripsByUser(stranger);

        assertThat(trips.size(), is(0));
    }

    @Test public void
    should_return_trips_when_users_are_friends() {

        User friend = aUser()
                                .friendsWith(ANOTHER_USER, REGISTERED_USER)
                                .withTripsTo(BRAZIL, LONDON)
                                .build();

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(2));
    }

    private class TestableTripService extends TripService {

        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }


}
