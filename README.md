# poketrader-api

## What is it?
This project is the backend of the [poketrader-app](https://github.com/leodealmeida/poketrader-app)

## How it works?

There are three exposedg endpoints to be consumed by the frontend app.
1.  POST ```/api/pokemon```
2.  POST ```/api/trade```
3.  GET ```/api/trades```

The first endpoint is used to save new pokemons to the database. 
The second is used to register a new trade, and the last one is used to fetch all
trades registered.

## Project setup
The following instructions assumes you have [gradle](https://gradle.org/) installed.

Create a new PostgreSQL database
```
create database <name>;
```

To run the project vie command line, run:

```
./gradlew bootRun --args='JDBC_DATABASE_URL=<name> JDBC_DATABASE_USERNAME=<user> JDBC_DATABASE_PASSWORD=<pass>'
```

I strongly recommend using IntelliJ if you are gonna write some code, it will save you a lot of time!


To run tests, just run:
```
gradle test
```

### Notes about the Solution
I tried to make the architecture clean and simple using four layers. They are:
1. Presentation
2. Application
3. Domain
4. Infrastructure

In the presentation layer lies the controllers. I tried to make them with no business logic. Their responsabilities are to listen to request, assert the request body (if any) are correct, and translate them into POJOs.

In the application layer lies the services (AppService). They are responsible for interact with database, other services and call business logic.

In the domain layer lies all the Entities with their own validations

Infrastructure is used to handle exception and other Spring Boot configurations.

If you have any trouble running the application or any feedbacks about the code, just let me know. I will be happy to help and listen.

