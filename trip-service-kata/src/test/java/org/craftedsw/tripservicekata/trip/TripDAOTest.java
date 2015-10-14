package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {

    @Test(expected = CollaboratorCallException.class) public void
    should_throw_an_exception_when_trips_are_returned() {
        new TripDAO().tripsBy(new User());

    }

}