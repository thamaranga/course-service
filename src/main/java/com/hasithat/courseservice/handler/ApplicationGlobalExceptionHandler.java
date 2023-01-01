package com.hasithat.courseservice.handler;

import com.hasithat.courseservice.dto.ErrorDTO;
import com.hasithat.courseservice.dto.ServiceResponse;
import com.hasithat.courseservice.exception.CourseNotFoundException;
import com.hasithat.courseservice.exception.CourseServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    //Handle MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        fieldErrors.forEach(fieldError -> {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(fieldError.getDefaultMessage());
            errorDTOList.add(errorDTO);
        });
        serviceResponse.setError(errorDTOList);
        serviceResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return serviceResponse;

    }

    //Handle CourseServiceBusinessException
    @ExceptionHandler(CourseServiceBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse<?> handleCourseServiceBusinessException(CourseServiceBusinessException ex) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        errorDTOList.add(new ErrorDTO(ex.getMessage()));
        serviceResponse.setError(errorDTOList);
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return serviceResponse;

    }

    //Handle CourseNotFoundException
    @ExceptionHandler(CourseNotFoundException.class)
    public ServiceResponse<?> handleCourseNotFoundException(CourseNotFoundException ex) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        errorDTOList.add(new ErrorDTO(ex.getMessage()));
        serviceResponse.setError(errorDTOList);
        serviceResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        return serviceResponse;

    }
}
