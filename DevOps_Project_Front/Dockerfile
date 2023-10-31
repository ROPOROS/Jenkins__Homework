FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf

COPY dist/summer-workshop-angular /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
