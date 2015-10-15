package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.craftedsw.tripservicekata.builder.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    @Test(expected = UserNotLoggedInException.class) public void
    should_validate_the_logged_in_user() {
        tripService.getTripsByUser(SOME_USER, GUEST);
    }

    @Test public void
    should_return_trips_when_users_are_friends() {

        User friend = aUser()
                .friendsWith(ANOTHER_USER, REGISTERED_USER)
                .withTripsTo(BRAZIL, LONDON)
                .build();
        given(tripDAO.tripsBy(friend)).willReturn(friend.trips());

        List<Trip> trips = tripService.getTripsByUser(friend, REGISTERED_USER);

        assertThat(trips.size(), is(2));
    }

    @Test public void
    should_not_return_any_trips_when_user_are_not_friends() {
        User stranger = aUser()
                                    .friendsWith(ANOTHER_USER)
                                    .withTripsTo(BRAZIL)
                                    .build();

        List<Trip> trips = tripService.getTripsByUser(stranger, REGISTERED_USER);

        assertThat(trips.size(), is(0));
    }


    private static final User GUEST = null;
    private static final User SOME_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip BRAZIL = new Trip();
    private static final Trip LONDON = new Trip();

    @Mock private TripDAO tripDAO;
    @InjectMocks private TripService tripService = new TripService();

}
