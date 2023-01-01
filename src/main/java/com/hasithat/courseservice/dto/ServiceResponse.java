package com.hasithat.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {

    private T response;
    private HttpStatus httpStatus;
    private List<ErrorDTO> error;

    public ServiceResponse(T response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }
}
