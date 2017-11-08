docker build -t auth0-servlet-webapp .
docker run -p 8080:8080 -it -v ~/.gradle:/home/gradle/.gradle auth0-servlet-webapp
