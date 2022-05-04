  # specify base docker image
  FROM node:alpine as builder

  # set default container workdir and fix idealTree already exists error along the way
  WORKDIR /usr/app/frontend

  # copy package json to container for npm install step
  COPY ./package.json ./

  # install dependencies
  RUN npm install

  # copy local files to container
  COPY ./ ./

  # follow terminal instruction
  RUN npx browserslist@latest --update-db

  # build frontend for deployment
  RUN npm run build

  # initialize production docker image
  FROM nginx:1.16.0-alpine

  # copy custom server config
  COPY ./nginx/server-config.conf /etc/nginx/conf.d/default.conf

  # copy frontend from previous image to production server
  COPY --from=builder /usr/app/frontend/build /usr/share/nginx/html
  
  # start production server
  CMD ["nginx", "-g", "daemon off;"]