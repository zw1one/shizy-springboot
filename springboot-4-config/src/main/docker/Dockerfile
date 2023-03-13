FROM openjdk:8-jdk-alpine
VOLUME ./service-logs
ADD hello-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-server","-Xmx1024m","-Xms256m","-XX:+UseParallelGC","-XX:ParallelGCThreads=20","-jar","/app.jar"]