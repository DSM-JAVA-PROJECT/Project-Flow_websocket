#!/bin/bash

#if [ -d /home/ubuntu/build ]; then
#    rm -rf /home/ubuntu/build   # 만약 build 디렉토리가 존재한다면 삭제.
#fi

#mkdir -vp /home/ubuntu/build
# 다시 새로운 /home/ubuntu/build 디렉토리를 생성합니다.

docker stop project-flow
docker rm project-flow
# Docker Container를 중지 및 제거

if [[ "$(docker images -q [Your DockerHub ID]/[Your Repository Name]:[Your version] 2> /dev/null)" != "" ]]; then
  docker rmi -f $(docker images --format '{{.Repository}}:{{.Tag}}' --filter=reference='[Your DockerHub ID]/[Your Repository Name]:[Your version]')
fi

echo "hello" >> test.log
# 해당 Docker Image가 존재하면 image를 제거