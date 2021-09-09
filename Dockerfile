FROM openjdk:16-jdk
ENV MONGO_URL $MONGO_URL
ENV SECRET_KEY $SECRET_KEY
ENV RABBIT_PORT $RABBIT_PORT
ENV RABBIT_USERNAME $RABBIT_USERNAME
ENV RABBIT_PASSWORD $RABBIT_PASSWORD
ENV RABBIT_HOST $RABBIT_HOST
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]