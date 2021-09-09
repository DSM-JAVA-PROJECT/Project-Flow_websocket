sudo docker pull jhhong0509/project-flow:latest
sudo docker run -d -p 8080:8080 --name project-flow jhhong0509/project-flow \
    -e MONGO_URL "$MONGO_URL" \
    -e SECRET_KEY "$SECRET_KEY" \
    -e RABBIT_PORT "$RABBIT_PORT" \
    -e RABBIT_USERNAME "$RABBIT_USERNAME" \
    -e RABBIT_PASSWORD "$RABBIT_PASSWORD" \
    -e RABBIT_HOST "$RABBIT_HOST"