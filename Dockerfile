FROM adoptopenjdk:11-jdk-hotspot-bionic as build

RUN curl https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz > maven.tar.gz
RUN tar xfv maven.tar.gz
ENV PATH $PATH:/apache-maven-3.6.3/bin

WORKDIR /app
COPY pom.xml .

RUN mvn -e -B clean package

COPY src ./src

RUN mvn -e -B clean package

FROM adoptopenjdk:11-jre-hotspot-bionic
COPY --from=build /app/target/socialnetwork-1.0-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
