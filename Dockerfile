FROM ghcr.io/graalvm/graalvm-ce:latest as BUILD
WORKDIR /work/
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn/
RUN ./mvnw dependency:go-offline
COPY . ./
RUN gu install native-image
RUN ./mvnw package -Pnative -Dmaven.test.skip=true

FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=BUILD /work/target/*-runner /application

EXPOSE 8080
USER nonroot

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]