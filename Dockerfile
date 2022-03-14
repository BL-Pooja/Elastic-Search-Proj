FROM maven:3.6.3-openjdk-15 AS builder
WORKDIR /app
COPY ./ /app
RUN chmod 775 /app/start.sh
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:15
WORKDIR /usr/local/src
COPY --from=builder /app/target/Ejet-Lead-Backend-0.0.1-SNAPSHOT.jar /usr/local/src
COPY --from=builder /app/start.sh /usr/local/src
ENTRYPOINT ["/usr/local/src/start.sh"]
