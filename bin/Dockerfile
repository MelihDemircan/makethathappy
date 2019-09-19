FROM java:8-jre

ADD ./target/highspeedtrain-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/highspeedtrain-0.0.1-SNAPSHOT.jar"]

EXPOSE 9090