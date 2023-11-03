FROM nginx:latest

WORKDIR /DevOps_Project_Front

EXPOSE 80

COPY DevOps_Project_Front/dist/summer-workshop-angular /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
