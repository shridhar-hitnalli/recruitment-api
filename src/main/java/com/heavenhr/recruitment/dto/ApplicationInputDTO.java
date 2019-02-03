package com.heavenhr.recruitment.dto;

import com.heavenhr.recruitment.entity.ApplicationStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by shridhar on 02/02/19.
 */
@Data
public class ApplicationInputDTO {

    @NotNull(message = "Candidate is required field")
    private CandidateDTO candidate;

    @ApiModelProperty(required = true, notes = "Resume test", example = "application resume text")
    @NotNull(message = "Resume text is required field")
    private String resumeText;

    @ApiModelProperty(required = true, notes = "Application status", example = "APPLIED")
    @NotNull(message = "Application status is required field")
    private ApplicationStatus status;
}
