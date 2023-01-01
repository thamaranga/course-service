package com.hasithat.courseservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hasithat.courseservice.annotation.CourseTypeValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course name shouldn't be NULL OR EMPTY")
    private String name;
    @NotEmpty(message = "Trainer name should be define")
    private String trainerName;
    @NotNull(message = "Duration must need to specify")
    private String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "Start date cannot be a past date")
    private Date startDate;
    /*Below annotation has been defined by me*/
    @CourseTypeValidation
    private String courseType;
    @Min(value = 1500, message = "Course fee cannot less than 1500")
    @Max(value = 5000, message = "Course fee cannot more than 5000")
    private double fees;
    /*
     * Below annotation is used since lombok generated setter name is setCertificateAvailable
     * and jackson is looking for a setter name  like setisCertificateAvailable.
     * So by adding @JsonProperty jackson will bind data using this filed and will
     * not look for the  relevant setter method.
     * */
    @JsonProperty
    private boolean isCertificateAvailable;
    @NotEmpty(message = "description must be present")
    @Length(min = 5, max = 20)
    private String description;
    @Email(message = "Invalid email id")
    private String emailId;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid contact number format")
    private String contact;

}
