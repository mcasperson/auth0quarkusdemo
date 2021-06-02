FROM ghcr.io/graalvm/graalvm-ce:latest as BUILD
WORKDIR /work/
COPY . ./
RUN gu install native-image
RUN ./mvnw package -Pnative

FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=BUILD /work/target/*-runner /application

EXPOSE 8080
USER nonroot

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]