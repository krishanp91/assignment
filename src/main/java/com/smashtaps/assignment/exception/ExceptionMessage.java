package com.smashtaps.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ExceptionMessage implements Serializable {
    private static final long serialVersionUID = -8491064098488248142L;

    private String message;
    private String error;
    private String path;
}
