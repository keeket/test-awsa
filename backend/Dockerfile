  # specify base docker image
  FROM maven:3.8.5-openjdk-11 as builder

  # set default container workdir and fix idealTree already exists error along the way
  WORKDIR /usr/app/backend

  # copy local files to container
  COPY ./ ./

  #  build app
  RUN ["mvn", "package"]

  # initialize production docker image
  FROM openjdk:8-jre-slim

  # copy frontend from previous image to production server
  COPY --from=builder /usr/app/backend/api-module/target/ /usr/app/backend

  # set working dir
  WORKDIR /usr/app/backend

  # start production server
  CMD ["java", "-jar", "api-module-0.0.1-SNAPSHOT.jar"]
