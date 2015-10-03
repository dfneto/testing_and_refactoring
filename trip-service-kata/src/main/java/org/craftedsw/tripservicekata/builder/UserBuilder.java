package org.craftedsw.tripservicekata.builder;

import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.user.User;

/**
 * Created by david on 03/10/2015.
 */
public class UserBuilder {

    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTripsTo(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addTripsTo(user);
        addFriendsTo(user);
        return user;
    }

    public void addTripsTo(User user) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }

    public void addFriendsTo(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }
}
