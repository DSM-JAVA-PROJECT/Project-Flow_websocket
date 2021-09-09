sudo docker pull project-flow-image
sudo docker run --env-file /home/ubuntu/.env -p 8080:8080 --name project-flow project-flow-image