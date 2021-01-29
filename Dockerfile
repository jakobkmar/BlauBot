ARG BUILD_DIR="/usr/src/BlauBot/"


FROM openjdk:11-slim AS builder

ARG BUILD_DIR

WORKDIR $BUILD_DIR
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew installDist --no-daemon


FROM openjdk:11-jre-slim

ARG BUILD_DIR

WORKDIR /app/
COPY --from=builder $BUILD_DIR/build/install/BlauBot/ .

CMD ["./bin/BlauBot"]
