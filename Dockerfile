FROM adoptopenjdk/openjdk8
VOLUME /tmp
ADD target/encurtador-app-0.0.1-SNAPSHOT.jar /encurtador-app-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/encurtador-app-0.0.1-SNAPSHOT.jar", "-Djava.security.egd=file:/dev/./urandom"]
EXPOSE 8080