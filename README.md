# HeavenHR java backend test (refer to [task.md](https://github.com/shridhar-hitnalli/recruitment-api/edit/master/task.md))

## Task Description
Build a backend service that handles a (very simple) recruiting process. The process requires two types of objects: job offers and applications from candidates.

### Prerequisites
* Java 1.8 or above
* Spring Boot
* Database H2 (In-Memory)
* Maven

## Running
Run the project by execute the command:
```
$ mvn spring-boot:run
```

While running the project you can access the following url in order to test it manually:
```
http://localhost:9999/
```

## Built With

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - The language used
* [Spring Boot 2.1.2](http://spring.io/projects/spring-boot) - The web framework used
* [Lombok](https://projectlombok.org/) - Lombok is used to reduce boilerplate code for model/data objects,
* [Maven](https://maven.apache.org/) - Dependency Management
* [Swagger](https://swagger.io/) - API Documentation


### Backend consists following restful apis
    a. v1/api/offers
    b. v1/api/applications


Swagger specs :

Swagger usually helps in the smart way of having technical documentation of the API. Please refer to the Swagger specs at http://localhost:9999/swagger-ui.html. URL .
At this URL you will know the structure of the request and the JSON response structure as well. You will also be able to hit the request from the swagger and get the json response back on the UI and play with it.
The app is currently configured to run on the port 9999 of the localhost ( In case you are running it on the local server ), however you are free to change it anytime you need ( In case you wish to deploy it to a different server ) by updating it in the application.properties file.


How to access the API?

Also as mentioned on the swagger specs, there are currently two APIs Application and Offer. You can access these APIs through Swagger ui
```
http://localhost:9999/swagger-ui.html
```

### Improvements:
1. Security can be added
2. Test cases can be added for controller integration tests
