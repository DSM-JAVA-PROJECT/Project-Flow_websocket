package com.projectflow.projectflowwebsocket.domain.project.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class ProjectNotFoundException extends GlobalException {

    public static final GlobalException EXCEPTION = new ProjectNotFoundException();

    private ProjectNotFoundException() {
        super(ErrorCode.PROJECT_NOT_FOUND);
    }
}
