package com.heavenhr.recruitment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by shridhar on 02/02/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDTO {

    @ApiModelProperty(required = true, notes = "Offer Id", example = "1")
    private Long id;

    @ApiModelProperty(required = true, notes = "Job title", example = "Software Engineer")
    private String jobTitle;

    @ApiModelProperty(notes = "Start date", example = "2019-02-03T13:17:12.934")
    private LocalDateTime startDate;

    @ApiModelProperty(notes = "Number of applications for this offer", example = "0")
    private Integer numberOfApplications;

}
