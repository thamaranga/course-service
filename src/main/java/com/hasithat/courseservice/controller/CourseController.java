package com.hasithat.courseservice.controller;

import com.hasithat.courseservice.dto.CourseRequestDTO;
import com.hasithat.courseservice.dto.CourseResponseDTO;
import com.hasithat.courseservice.dto.ServiceResponse;
import com.hasithat.courseservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    /*Below two annotations @Operation and @ApiResponses are related to swagger*/
    @Operation(summary = "add a new course to the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "course added successfully"), @ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO newCourse = courseService.addCourse(courseRequestDTO);
        return new ServiceResponse<>(newCourse, HttpStatus.CREATED);//201
    }

    @Operation(summary = "Fetch all course objects")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "success"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourses() {
        List<CourseResponseDTO> CourseResponseDTOs = courseService.viewAllCourses();
        return new ServiceResponse<>(CourseResponseDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Find course by courseId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "success"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/{id}")
    public ServiceResponse<CourseResponseDTO> findCourseById(@PathVariable("id") int courseId) {
        CourseResponseDTO courseResponseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponse<>(courseResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete course by courseId")
    @DeleteMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return new ServiceResponse<>(new CourseResponseDTO(), HttpStatus.OK);
    }

    @PutMapping("/{courseId}")
    @Operation(summary = "update the course in system")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course) {
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, course);
        return new ServiceResponse<>(courseResponseDTO, HttpStatus.OK);
    }


}
