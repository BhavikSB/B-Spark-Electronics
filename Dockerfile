# Use Tomcat 9 directly
FROM tomcat:9.0-jdk17

# Clear out default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your pre-built WAR file and rename it to ROOT.war so it loads on the main URL
COPY B-Spark_Electronics.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]