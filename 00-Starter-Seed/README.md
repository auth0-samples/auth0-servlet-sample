# Starter Seed for Auth0-Java Servlet Quickstart Guides

This is a simple Java web application that is meant to be used as the starting point for [Auth0 Java Servlet Quickstart](https://auth0.com/docs/quickstart/webapp/java). It demonstrates how to use Auth0 with Java for server-side MVC web apps.

## Prerequisites

In order to run this example you will need to have Java 7+ and Maven installed.

Check that your maven version is 3.0.x or above:

```sh
mvn -v
```

## Setup

Create an [Auth0 Account](https://auth0.com).

Create an application - for the purposes of this sample - `app`

Ensure you add the following to the settings.

Allowed Callback URL:

```
http://localhost:3099/callback
```

Ensure you add the following to the settings.

Allowed Logout URLs:

```
http://localhost:3099/logout
```

Add one or more `connections` to your application - for instance Google Social Connection,
or username-password DB connection.


## Update configuration information

Enter your:

`client_id`, `client_secret`, and `domain` information into `src/main/webapp/WEB-INF/web.xml`

You can retrieve this info from the settings of your application at the Auth0 dashboard.


## Build and Run

In order to build and run the project you must execute:

```sh
mvn clean install tomcat7:run
```

Then, go to [http://localhost:3099/login](http://localhost:3099/login).

