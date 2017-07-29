/**
 * Chris Book Store.
 */


package com.chris.bookstore.users;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author chris
 *
 */

@Repository
public class UserRoleRepository {
    public List<String> findRoleByUserName(String username){
    	List<String> roles;
		switch (username) {
		case "admin":
			roles = Arrays.asList("ROLE_ADMIN");
			break;
		case "user":
			roles = Arrays.asList("ROLE_USER");
			break;
		default:
			throw new IllegalArgumentException("Invalid user name.");
		}
		return roles;

    }
}
