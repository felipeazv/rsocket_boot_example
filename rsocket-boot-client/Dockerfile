# Start with a base image containing Java runtime
FROM openjdk:11.0-jre-slim
# Add the application's jar to the container
ADD target/rsocket-boot-client.jar rsocket-boot-client.jar
# Make port 8080 available to the world outside this container
EXPOSE 8080
# Run the jar file
ENTRYPOINT exec java -Dspring.profiles.active=docker -jar /rsocket-boot-client.jar