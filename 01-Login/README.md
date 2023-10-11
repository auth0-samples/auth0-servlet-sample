
# Auth0 Servlet Sample

This sample demonstrates how to use Auth0 to perform authentication using the Auth0 Java MVC Commons library in a Java Servlet web application. Download or clone this repository and follow the instructions below to configure and run the application.

To learn more about the Auth0 Java MVC Commons library, refer to the project's [documentation](https://github.com/auth0/auth0-java-mvc-common/blob/master/README.md).

## Prerequisites

- Java 8 or greater
- An Auth0 account

## Configuration

### Auth0 Dashboard
1. On the [Auth0 Dashboard](https://manage.auth0.com/#/clients) create a new Application of type **Regular Web Application**.
1. On the **Settings** tab of your application, add the URL `http://localhost:3000/callback` to the **Allowed Callback URLs** field.
1. On the **Settings** tab of your application, add the URL `http://localhost:3000/login` to the **Allowed Logout URLs** field.
1. Save the changes to your application settings. Don't close this page; you'll need some of the settings when configuring the application below.

### Application configuration

Set the Auth0 Application values from above in the `src/main/webapp/WEB-INF/web.xml` file.

```xml
<context-param>
    <param-name>com.auth0.domain</param-name>
    <param-value>{YOUR_AUTH0_DOMAIN}</param-value>
</context-param>

<context-param>
    <param-name>com.auth0.clientId</param-name>
    <param-value>{YOUR_AUTH0_CLIENT_ID}</param-value>
</context-param>

<context-param>
    <param-name>com.auth0.clientSecret</param-name>
    <param-value>{YOUR_AUTH0_CLIENT_SECRET}</param-value>
</context-param>
```

### Running the sample

Open a terminal or command line, navigate to the `01-Login` directory, and run the following command:

```bash
./gradlew clean appRun
```

If you are using a Windows environment, run the following command:

```bash
gradlew clean appRun
```

The server will be accessible on http://localhost:3000/portal/home. After logging in you should see the `token` in the header.

### Running the sample with Docker

In order to run the example with docker you need to have `docker` installed.

You also need to set the client credentials as explained [previously](#java-application).

Execute in command line `sh exec.sh` to run the Docker in Linux, or `.\exec.ps1` to run the Docker in Windows.

## Issue Reporting

If you have found a bug or if you have a feature request, please report them at this repository issues section. Please do not report security vulnerabilities on the public GitHub issue tracker. The [Responsible Disclosure Program](https://auth0.com/whitehat) details the procedure for disclosing security issues.

## What is Auth0?

Auth0 helps you to:

* Add authentication with [multiple authentication sources](https://docs.auth0.com/identityproviders), either social like **Google, Facebook, Microsoft Account, LinkedIn, GitHub, Twitter, Box, Salesforce, amont others**, or enterprise identity systems like **Windows Azure AD, Google Apps, Active Directory, ADFS or any SAML Identity Provider**.
* Add authentication through more traditional **[username/password databases](https://docs.auth0.com/mysql-connection-tutorial)**.
* Add support for **[linking different user accounts](https://docs.auth0.com/link-accounts)** with the same user.
* Support for generating signed [Json Web Tokens](https://docs.auth0.com/jwt) to call your APIs and **flow the user identity** securely.
* Analytics of how, when and where users are logging in.
* Pull data from other sources and add it to the user profile, through [JavaScript rules](https://docs.auth0.com/rules).

## Create a free account in Auth0

1. Go to [Auth0](https://auth0.com) and click Sign Up.
2. Use Google, GitHub or Microsoft Account to login.

## Issue Reporting

If you have found a bug or if you have a feature request, please report them at this repository issues section. Please do not report security vulnerabilities on the public GitHub issue tracker. The [Responsible Disclosure Program](https://auth0.com/whitehat) details the procedure for disclosing security issues.

## Author

[Auth0](https://auth0.com)

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE.txt) file for more info.

