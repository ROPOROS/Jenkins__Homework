FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf

COPY dist/summer-workshop-angular /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
