# BookStore

Intro

BookStore demonstration, written by Spring boot1.5 & Boostrap4. The project is a little bit different from the traditional jsp/java website, Maybe, we can named it as 'partial refresh'.  Means, we use ajax tech to refresh a block in partial page.  The cost of development & study is less. But it will improve the user friendly effectively.


Application Features:

* Ajax Based UI integrated Spring MVC
* Restful API is provided(It can be used in CommandLine, or others clients like android, JS based application)
* JPA/Embeded dataBase
* Athentication system based on Spring Security
* Data Init Interface
* Well design both including UE/UE & Software architeture  


Business Functions:

* Book searching
* Book ordering
* User login


Dependencies: 

* Spring Boot 1.5
* Bootstrap 4.0
* Google Guava


API description:

	Anonymous API
	* /login
	* /logout
	* /api/books/get/{id}
	* /api/books/get/{title}	
	* /api/books/list
	* /api/books/list/{keyword}
	
	User API
	* /api/books/user/buy   GET
	* /api/books/user/buy 	POST
	
	Admin API
	* /api/books/admin/add   GET
	* /api/books/admin/add 	 POST
	* /api/books/admin/init  GET
	* /api/books/admin/init  POST

	USAGE:	there are lots of tools : curl -d username=user -d password=user  --cookie-jar ./cookie -L http://localhost:8080/login | jq .


Ajax Based JSP Description:
	
	localhost:8080 to show 
	
