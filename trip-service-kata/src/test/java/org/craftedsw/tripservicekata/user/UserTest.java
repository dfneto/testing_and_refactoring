package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.craftedsw.tripservicekata.builder.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private static final User PAUL = new User();
    private static final User BOB = new User();

    @Test public void
    should_inform_when_users_are_not_friends() {
        User user  = aUser().friendsWith(PAUL).build();

        assertThat(user.isFriendWith(BOB), is(false));
    }

    @Test public void
    should_inform_when_user_are_friends() {
        User user  = aUser().friendsWith(PAUL, BOB).build();

        assertThat(user.isFriendWith(BOB), is(true));
    }

}
