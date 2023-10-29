FROM nginx:latest

RUN rm /etc/nginx/conf.d/default.conf

COPY /app/dist/DevOps_Project_Front /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
