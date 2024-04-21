FROM bellsoft/liberica-openjdk-debian:17

RUN adduser --system patient-domain && addgroup --system patient-domain && adduser patient-domain patient-domain
USER patient-domain

WORKDIR /app

COPY target/dependency ./lib
COPY target/patient-domain-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]
