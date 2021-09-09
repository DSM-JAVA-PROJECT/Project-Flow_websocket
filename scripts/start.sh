sudo docker pull jhhong0509/project-flow-image
sudo docker run --env-file /home/ubuntu/.env -p 8080:8080 --name project-flow project-flow-image