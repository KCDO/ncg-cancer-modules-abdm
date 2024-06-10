FROM amazoncorretto:21-alpine
RUN addgroup -g 1001 -S appuser && adduser -u 1001 -S appuser -G appuser
RUN mkdir /app && chown -R appuser:appuser /app
USER appuser
WORKDIR /app
COPY ./src/main/resources/  /app/resources/
COPY $TARGET/*.jar /app/app.jar
RUN find /app/resources/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dserver.tomcat.accesslog.enabled=true", "app.jar"]