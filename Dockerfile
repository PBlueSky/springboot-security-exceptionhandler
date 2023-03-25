FROM liferay/jdk11
WORKDIR /app
COPY ./target/*.jar /app
CMD ["java", "-jar", "springboot-exception-handler-0.0.1-SNAPSHOT.jar"]