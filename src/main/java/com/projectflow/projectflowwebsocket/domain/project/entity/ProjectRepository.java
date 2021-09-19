package com.projectflow.projectflowwebsocket.domain.project.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
