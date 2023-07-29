FROM maven:3-eclipse-temurin-17 AS builder

RUN mkdir /app
WORKDIR /app

ADD pom.xml ./
RUN mvn install

ADD ./src src/
RUN mvn --batch-mode package -DskipTests=true

FROM eclipse-temurin:17

ENV APP_USER app
ENV APP_HOME /usr/local/backend

ARG ENV=PROD
ENV ENV=${ENV}

RUN addgroup --system $APP_USER && adduser --system --disabled-password --no-create-home --ingroup $APP_USER $APP_USER
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

COPY --from=builder /app/target/backend.jar backend.jar

RUN chown -R $APP_USER:$APP_USER $APP_HOME
USER $APP_USER

EXPOSE 8080

CMD ["java", "-Xmx1024m", "-Xms512m", "-jar", "/usr/local/backend/backend.jar"]
