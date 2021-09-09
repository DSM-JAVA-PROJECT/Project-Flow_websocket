#!/bin/bash

#if [ -d /home/ubuntu/build ]; then
#    rm -rf /home/ubuntu/build   # 만약 build 디렉토리가 존재한다면 삭제.
#fi

#mkdir -vp /home/ubuntu/build
# 다시 새로운 /home/ubuntu/build 디렉토리를 생성합니다.

sudo docker stop project-flow
sudo docker rm project-flow
# Docker Container를 중지 및 제거

if [[ "$(sudo docker images -q 'jhhong0509/project-flow:latest' 2> /dev/null)" != "" ]]; then
  sudo docker rmi -f $(sudo docker images --format '{{.Repository}}:{{.Tag}}' --filter=reference='jhhong0509/project-flow:latest')
fi

echo "hello" >> test.log
# 해당 Docker Image가 존재하면 image를 제거