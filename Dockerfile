FROM gradle:7.4.2-jdk17-alpine as base
WORKDIR /home/gradle/project
COPY ./ /home/gradle/project
RUN gradle build

FROM openjdk:17-jdk-alpine as app
WORKDIR /home/gradle/project
COPY --from=base /home/gradle/project/app/build/libs/app-2.0-INDEV-all.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]