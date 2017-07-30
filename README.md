# BookStore

Intro

BookStore demonstration, written by Spring boot1.5 & Boostrap4. Providing a full function by Restful api to meet the requirement of separation between frontend and backend . This project's frontend was done by Spring MVC. But was a little bit different from the traditional jsp/servlet website, Maybe, we can named it as 'partial refresh'.  Means, we use ajax tech to refresh a block in partial page.  The cost of development & study is less. But it will improve the user friendly effectively. 

Demo show
* [https://youtu.be/929qJmgO93Y](https://youtu.be/929qJmgO93Y)

Business Functions:

* Book searching
* Book ordering
* User login/authentication

Technical Features:

* Ajax Based UI integrated Spring MVC
* Restful API is provided(It can be used in CommandLine, or others clients like mobile apps, JS based apps)
* Well design for the purpose of distinction between frontend and backend
* JPA/Embedded dataBase
* Authentication system based on Spring Security
* DataBase/Data Init Interface


Testing:
* Unit test for servcie & repository
* Testing in Ubuntu/Window10 for server.
* Well testing & validation for frontend in Chrome.
* API tested in command line by curl tools.

Dependencies: 

* Spring Boot 1.5
* Bootstrap 4.0
* Google Guava

API description:

	Anonymous API(GET API):
	* /login
	* /logout
	* /api/books/get/{id}
	* /api/books/get/{title}	
	* /api/books/list
	* /api/books/list/{keyword}
	
	User API:
	* /api/books/user/buy   GET
	* /api/books/user/buy 	POST
	
	Admin API:
	* /api/books/admin/add   GET
	* /api/books/admin/add 	 POST
	* /api/books/admin/init  GET
	* /api/books/admin/init  POST



Ajax Based JSP Description:
	
	open the url localhost:8080 to show the site in chrome 
	
		* home.jsp (home page)
		* books.jsp (books block in home page)
		* user.jsp (user block in home page[up-right])
		* login.jsp (login dialog block in home page[shown when click login button])
		---
		* BookController (handling book related)
		* UserController (handling user related)
	
Access Restful API by command line(CLI):

	We can use curl tool to access the BookStore's backend by command line. 
		
		Login:
			curl -d username=user -d password=user  --cookie-jar ./cookie -L http://localhost:8080/login | jq .
		
		Init database(Admin role):
			curl --cookie ./cookie -X POST -H "Content-type: application/json" -X POST http://localhost:8080/api/books/admin/init -d '
				{
					"url":"https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt",
					"splitter":";"

				} 
				' | jq .

		Put a new book(Admin role):
			curl --cookie ./cookie -X POST -H "Content-type: application/json" -X POST http://localhost:8080/api/books/admin/add -d '
				{
					"title":"Gone with wind",
					"author":"kris",
					"price":123.99,
					"quantity":200
				}
				' | jq .	

		Get a book:
			curl --cookie ./cookie http://localhost:8080/api/books/get/1 | jq .

		Get a book by title:
			curl --cookie ./cookie http://localhost:8080/api/books/get/Gone%20with%20wind | jq .
		
		List books:
			curl --cookie ./cookie http://localhost:8080/api/books/list | jq .

		List books by keyword
			curl --cookie ./cookie http://localhost:8080/api/books/list/Gone%20with%20wind | jq .

		

		
	
	
	
	
	
	
	
		