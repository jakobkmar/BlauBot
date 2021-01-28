FROM openjdk:11-jre-slim

COPY ./build/install/BlauBot /app/

WORKDIR /app/

CMD ["./bin/BlauBot"]
