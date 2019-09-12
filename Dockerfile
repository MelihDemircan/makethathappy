FROM java:8-jre
MAINTAINER Selahaddin Akgun <selahaddin.akgun@fmsstech.com>

ADD ./target/customer-service.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/customer-service.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:9011/actuator/health || exit 1

EXPOSE 9011