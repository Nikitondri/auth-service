FROM bellsoft/liberica-openjdk-debian:17

RUN adduser --system db-migrations && addgroup --system db-migrations && adduser db-migrations db-migrations
USER db-migrations

WORKDIR /app

COPY target/dependency ./lib
COPY target/db-migrations-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]
