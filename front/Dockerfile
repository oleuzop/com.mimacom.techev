FROM node:alpine AS builder

WORKDIR /front

COPY . .

RUN npm install && npm run build

FROM nginx:alpine

COPY --from=builder front/dist/* /usr/share/nginx/html/
