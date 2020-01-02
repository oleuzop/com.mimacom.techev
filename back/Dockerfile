FROM maven:3.6.3-jdk-8

VOLUME /back

COPY . .

EXPOSE 8080

RUN mvn clean install

ENTRYPOINT ["mvn","spring-boot:run"]
