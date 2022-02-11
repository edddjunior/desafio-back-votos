FROM maven:3.6-jdk-11 AS build


WORKDIR /usr/src/app
ADD pom.xml pom.xml

RUN mvn verify clean --fail-never

COPY src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml clean package

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]