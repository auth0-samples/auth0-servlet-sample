docker build -t auth0-servlet-webapp .
docker run -p 3000:3000 -it -v ~/.gradle:/home/gradle/.gradle auth0-servlet-webapp
