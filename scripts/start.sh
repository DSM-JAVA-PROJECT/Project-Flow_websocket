docker pull project-flow-image
docker run --env-file /home/ubuntu/.env -p 8080:8080 --name project-flow project-flow-image