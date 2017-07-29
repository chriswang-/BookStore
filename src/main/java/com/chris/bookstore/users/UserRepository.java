/**
 * Chris Book Store.
 */

package com.chris.bookstore.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * @author chris
 *
 */
@Repository
public class UserRepository {
	public User findByUserName(String username) {
		User ru;
		BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

		switch (username) {
		case "admin":
			ru = new User(new Long("1"), "admin", passwordEncoder.encode("admin"));
			break;
		case "user":
			ru = new User(new Long("2"), "user", passwordEncoder.encode("user"));
			break;
		default:
			throw new IllegalArgumentException("Invalid user name.");
		}
		return ru;
		
	}

}
