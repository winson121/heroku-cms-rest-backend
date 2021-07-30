# heroku-cms-rest-backend
simple course management system Backend REST api built using springboot with Spring MVC, Spring Security, and Spring Hibernate. This is the backend REST API server for the simple [course management system webapp](https://github.com/winson121/heroku-cms-webapp).

# Table of Contents
* [Overview](#overview)

<a id="overview" />

## Overview
This REST API server contained resources needed by the heroku-cms-webapp. The cms webapp will first make a HttpRequest to the RestController through the REST API url. Before the HttpRequest being process by the [RestController](https://github.com/winson121/heroku-cms-rest-backend/tree/main/src/main/java/com/springcms/backendrestapi/controller), the Spring Security filter will intercept the HttpRequest and authenticate the users by reading the Authorization token in the HttpRequest Headers. If no Authorization token is provided, then the request will be rejected right away. After the request is authenticated, the RestController will then provide the resources requested by the cms webapp or perform put/post/delete operation to save the data into the database. The RestController will first call the corresponding CRUD method from the Service object. This service object will either preprocess the data before calling the DAO object, which an object that provide interface between the Database. The service object act as common interface for DAO subsystem, which make use of [Facade design pattern](https://www.tutorialspoint.com/design_pattern/facade_pattern.htm). The DAO object will then open a [Hibernate](https://www.javatpoint.com/hibernate-tutorial) session and perform a CRUD transaction and commit the transaction to the database.
The following is the REST API architecture:


![cms-rest-backend](https://user-images.githubusercontent.com/45975038/127633433-d2705d8f-e5ff-49ad-bd31-7e096c24d177.png)

## CMS Entity Relationship
Following is the ERD for our CMS system, which also includes the user login ERD to determine the role of the user:

![cms-erd](https://user-images.githubusercontent.com/45975038/127633492-f6e770df-0de8-440f-a16c-3f543ab911b5.png)

The way we design the ERD will affect the way we fetch the data using hibernate. In our Entity Object, we use EAGER Fetch strategy for fetching role attribute in user and instructor attribute for course object since the data to fetch is constant and only consume small amount of RAM when loaded. As for @ManyToMany relationship between courses attribute in user object and users attribute in course object, we use the LAZY fetch strategy. The LAZY Fetch strategy will allow us to only load the course data excluding the related users data so that we don't consume memory resources when we don't need to get the related attribute data. The reason to use lazy fetch stategy is that the amount of the space consume by either courses or users attribute is not constant as a new user may be added to the course's users attribute when a user enrolled to that course or a new course could be created and added to the user's courses attribute when a user created a new course. Learn more on Hibernate Fetch strategy on this [link](https://vladmihalcea.com/hibernate-facts-the-importance-of-fetch-strategy/).



