package com.hasithat.courseservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String name;
    private String trainerName;
    private String duration;
    private Date startDate;
    private String courseType;
    private double fees;
    private boolean isCertificateAvailable;
    private String description;
    private String emailId;
    private String contact;


}
