FROM openjdk:8-jre

MAINTAINER Yusupov Dmitry

ADD build/libs/shop*.jar /app/shop.jar

CMD ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8888","-Dspring.profiles.active=dev-h2","-jar","/app/shop.jar"]
