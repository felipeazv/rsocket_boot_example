# Start with a base image containing Java runtime
FROM openjdk:11.0-jre-slim
# Add the application's jar to the container
ADD target/rsocket-boot-server.jar rsocket-boot-server.jar
# Make port 9999 available to the world outside this container
EXPOSE 9999
# Run the jar file
ENTRYPOINT exec java -Dspring.profiles.active=docker -jar /rsocket-boot-server.jar -Dspring.profiles.active=docker