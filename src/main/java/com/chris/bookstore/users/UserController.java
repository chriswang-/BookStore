/**
 * Chris Book Store.
 */


package com.chris.bookstore.users;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chris.bookstore.books.persistent.Book;

/**
 * @author chris
 *
 */

@Controller
@RequestMapping("/users")
public class UserController {
	
	/**
	 * User Info block.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"get" }) 
	public String get(Model model) {
	    return "user";
	}
	
	
	/**
	 * User Login Dialog.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"login" }) 
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {
		

		if(null == error) {
		    return "login";
		}		
		
		if(error.startsWith("Invalid user name")) {
			model.addAttribute("name", error);
		}else {
			model.addAttribute("password", error);
		}
		
	    return "login";
	}
	
	
	
}
