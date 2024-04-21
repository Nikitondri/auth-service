FROM bellsoft/liberica-openjdk-debian:17

RUN adduser --system gateway-service && addgroup --system gateway-service && adduser gateway-service gateway-service
USER gateway-service

WORKDIR /app

COPY target/dependency ./lib
COPY target/gateway-service-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]
