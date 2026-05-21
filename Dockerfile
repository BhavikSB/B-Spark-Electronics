# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# This builds your project and creates the .war file
RUN mvn clean package

# Stage 2: Run it on Tomcat 9 (since you are using 9.0.97)
FROM tomcat:9.0-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*

# This copies your newly built .war file and renames it to ROOT.war
# Renaming it to ROOT.war ensures your site opens at the main URL (/) 
# instead of /B-Spark_Electronics
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]