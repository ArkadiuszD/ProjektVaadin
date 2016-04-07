package my.vaadin.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

	private static List<User> listOfUsers = new ArrayList<User>();

	public long addUser(User user) {
		listOfUsers.add(user);
		return 1;
	}

	public long deleteUser(User user) {
		listOfUsers.remove(listOfUsers.indexOf(user));
		return 1;
	}

	public List<User> getUsers() {
		return listOfUsers;
	}



	public boolean checkPassword(User user) {
		for (User u : getUsers()) {
			if (u.getName().equals(user.getName()) && u.getPass().equals(user.getPass())) {
				return true;
			}
		}
		return false;
	}

}