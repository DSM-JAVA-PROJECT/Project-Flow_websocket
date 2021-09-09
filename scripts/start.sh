sudo docker pull jhhong0509/project-flow:latest
sudo docker run -d --env-file /home/ubuntu/.env -p 8080:8080 --name project-flow jhhong0509/project-flow