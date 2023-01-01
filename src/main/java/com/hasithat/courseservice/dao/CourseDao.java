package com.hasithat.courseservice.dao;

import com.hasithat.courseservice.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<CourseEntity, Integer> {
}
