package com.heavenhr.recruitment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by shridhar on 02/02/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferInputDTO {

    @ApiModelProperty(required = true, notes = "Job title", example = "Software Engineer")
    @NotNull(message = "Job title is required field")
    private String jobTitle;

}
