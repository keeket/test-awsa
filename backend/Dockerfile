  # specify base docker image
  FROM maven:3.8.5-openjdk-11

  # set default container workdir and fix idealTree already exists error along the way
  WORKDIR /usr/app/backend

  # copy local files to container
  COPY ./ ./

  #  build app
  RUN ["mvn", "package"]

  #  run app
  CMD ["mvn", "spring-boot:run"]

  # bouw app met:   docker build -t <dockerhub account>/mer-test-be:latest .
  # start app met:  docker run -p 8180:8080 <dockerhub account>/mer-test-be
  # app is te bereiken op localhost:8180