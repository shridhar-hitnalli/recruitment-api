package com.heavenhr.recruitment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavenhr.recruitment.entity.ApplicationStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shridhar on 02/02/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationDTO {

    @ApiModelProperty(notes = "Application Id", example = "11")
    private Long id;

    private OfferDTO offer;

    private CandidateDTO candidate;

    @ApiModelProperty(required = true, notes = "Resume test", example = "application resume text")
    private String resumeText;

    @ApiModelProperty(required = true, notes = "Application status", example = "APPLIED")
    private ApplicationStatus status;
}
