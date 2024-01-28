package com.musalasoft.drones.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class ExceptionResponse {
    private Boolean isSuccessful;
    private int status_code;
    private String path;
    private String message;
    private Object errors;
    private String timeStamp;


    public ExceptionResponse(int status, Map<String, Object> errorAttributes)  {
        Object json =  errorAttributes.get("errors");
        this.setIsSuccessful(false);
        this.setPath((String) errorAttributes.get("path"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTimeStamp(errorAttributes.get("timestamp").toString());
        this.setErrors(json);
        this.setStatus_code(status);
    }


}