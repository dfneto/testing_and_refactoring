package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;

public class UserSession {

	private static final UserSession userSession = new UserSession();
	
	private UserSession() {
	}
	
	public static UserSession getInstance() {
		return userSession;
	}

	public User getLoggedInUser() {
		throw new CollaboratorCallException(
				"UserSession.getLoggedInUser() should not be called in an unit test");
	}

}
