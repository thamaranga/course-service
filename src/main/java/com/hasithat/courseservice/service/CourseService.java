package com.hasithat.courseservice.service;

import com.hasithat.courseservice.controller.CourseController;
import com.hasithat.courseservice.dao.CourseDao;
import com.hasithat.courseservice.dto.CourseRequestDTO;
import com.hasithat.courseservice.dto.CourseResponseDTO;
import com.hasithat.courseservice.entity.CourseEntity;
import com.hasithat.courseservice.exception.CourseNotFoundException;
import com.hasithat.courseservice.exception.CourseServiceBusinessException;
import com.hasithat.courseservice.util.AppUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
//@AllArgsConstructor
public class CourseService {

    Logger log = LoggerFactory.getLogger(CourseService.class);


    @Autowired
    CourseDao courseDao;

    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO) {
        try {
            CourseEntity savedCourseEntity = courseDao.save(AppUtils.mapDTOToEntity(courseRequestDTO));
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(savedCourseEntity);
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            return courseResponseDTO;
        } catch (CourseServiceBusinessException e) {
            log.error(e.getMessage());
            throw new CourseServiceBusinessException("Something went wrong. Please try again later.");
        }
    }

    public List<CourseResponseDTO> viewAllCourses() {
        try {
            Iterable<CourseEntity> courseEntities = courseDao.findAll();
            List<CourseResponseDTO> courseResponseDTOs = StreamSupport.stream(courseEntities.spliterator(), false).map(courseEntity -> AppUtils.mapEntityToDTO(courseEntity)).collect(Collectors.toList());
            if (courseResponseDTOs.size() > 0) {
                return courseResponseDTOs;
            } else {
                throw new CourseNotFoundException("No courses found");
            }


        } catch (CourseServiceBusinessException e) {
            log.error(e.getMessage());
            throw new CourseServiceBusinessException("Something went wrong. Please try again later.");
        }
    }

    public CourseResponseDTO findByCourseId(int courseId) {
        try {
            CourseEntity courseEntity = courseDao.findById(courseId).orElseThrow(() -> new CourseNotFoundException("No course data found : " + courseId));
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(courseEntity);
            return courseResponseDTO;
        } catch (CourseServiceBusinessException e) {
            log.error(e.getMessage());
            throw new CourseServiceBusinessException("Something went wrong. Please try again later.");
        }
    }

    public void deleteCourse(int courseId) {
        try {
            CourseEntity existingCourse = courseDao.findById(courseId).orElse(null);
            if (existingCourse != null) {
                courseDao.deleteById(courseId);
            } else {
                throw new CourseNotFoundException("No course data found : " + courseId);
            }

        } catch (CourseServiceBusinessException e) {
            log.error(e.getMessage());
            throw new CourseServiceBusinessException("Something went wrong. Please try again later.");
        }
    }

    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO newCourse) {
        try {
            CourseEntity existingCourse = courseDao.findById(courseId).orElse(null);
            if (existingCourse != null) {
                existingCourse.setCourseType(newCourse.getCourseType());
                existingCourse.setFees(newCourse.getFees());
                existingCourse.setName(newCourse.getName());
                existingCourse.setDuration(newCourse.getDuration());
                existingCourse.setDescription(newCourse.getDescription());
                existingCourse.setCertificateAvailable(newCourse.isCertificateAvailable());
                existingCourse.setTrainerName(newCourse.getTrainerName());
                existingCourse.setStartDate(newCourse.getStartDate());
                CourseEntity updatedCourse = courseDao.save(existingCourse);
                return AppUtils.mapEntityToDTO(updatedCourse);
            } else {
                throw new CourseNotFoundException("No course data found : " + courseId);
            }

        } catch (CourseServiceBusinessException e) {
            log.error(e.getMessage());
            throw new CourseServiceBusinessException("Something went wrong. Please try again later.");
        }
    }


    public String logTest() {
        log.trace("Log message from trace");
        log.debug("Log message from debug");
        log.info("Log message from info");
        log.warn("Log message from warn");
        log.error("Log message from error");
        return "Hello.....";
    }
}
