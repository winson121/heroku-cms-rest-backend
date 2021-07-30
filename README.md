# heroku-cms-rest-backend
simple course management system Backend REST api built using springboot with Spring MVC, Spring Security, and Spring Hibernate. This is the backend REST API server for the simple [course management system webapp](https://github.com/winson121/heroku-cms-webapp).

# Table of Contents
* [Overview](#overview)

<a id="overview" />

## Overview
This REST API server contained resources needed by the heroku-cms-webapp. The cms webapp will first make a HttpRequest to the RestController through the REST API url. Before the HttpRequest being process by the RestController, the Spring Security filter will intercept the HttpRequest and authenticate the users by reading the Authorization token in the HttpRequest Headers. If no Authorization token is provided, then the request will be rejected right away. After the request is authenticated, the RestController will then provide the resources requested by the cms webapp or perform put/post/delete operation to save the data into the database. The RestController will first call the corresponding CRUD method from the Service object. This service object will either preprocess the data before calling the DAO object, which an object that provide interface between the Database. The service object act as common interface for DAO subsystem, which make use of [Facade design pattern](https://www.tutorialspoint.com/design_pattern/facade_pattern.htm). The DAO object will then open a [Hibernate](https://www.javatpoint.com/hibernate-tutorial) session and perform a CRUD transaction and commit the transaction to the database.
The following is the REST API architecture:


![cms-rest-backend](https://user-images.githubusercontent.com/45975038/127633433-d2705d8f-e5ff-49ad-bd31-7e096c24d177.png)


