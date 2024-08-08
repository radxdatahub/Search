FROM openjdk:22-ea-17-jdk-slim
EXPOSE 8080

ARG CERT
ARG JAR_FILE=target/radx-search-v3-0.0.1-SNAPSHOT.jar

ENV SEARCH_USERNAME={INSERT USERNAME HERE}
ENV SEARCH_PASSWORD={INSERT PASSWORD HERE}


#Instruction to copy files from local source to container target
COPY ${JAR_FILE} app.jar

ENTRYPOINT java -jar app.jar
