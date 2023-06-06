FROM --platform=linux/amd64 openjdk:17-alpine
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*
HEALTHCHECK --interval=30s --timeout=4s CMD curl -f http://localhost:8080/actuator/health || exit 1

WORKDIR /opt/banktransfer
COPY target/Bank-Transfer-*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]