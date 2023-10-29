FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf

COPY /DevOps_Project_Front/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
