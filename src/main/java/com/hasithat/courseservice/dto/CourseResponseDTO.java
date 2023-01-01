package com.hasithat.courseservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {

    private int courseId;
    private String name;
    private String trainerName;
    private String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
    private String courseType;
    private double fees;
    private boolean isCertificateAvailable;
    private String description;
    private String courseUniqueCode;
    private String emailId;
    private String contact;


}
