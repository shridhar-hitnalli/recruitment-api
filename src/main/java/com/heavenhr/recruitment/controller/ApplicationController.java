package com.heavenhr.recruitment.controller;

import com.heavenhr.recruitment.dto.ApplicationDTO;
import com.heavenhr.recruitment.dto.ApplicationInputDTO;
import com.heavenhr.recruitment.entity.ApplicationStatus;
import com.heavenhr.recruitment.service.application.ApplicationService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by shridhar on 02/02/19.
 */
@Validated
@RestController
@RequestMapping(value = "v1/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "Application", value = "Application")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful read"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 409, message = "Conflict"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class ApplicationController {

    private static final String OFFER_ID = "offerId";
    private static final String APPLICATION_ID = "applicationId";

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @ApiOperation(value = "Retrieve all applications")
    @GetMapping(value = "/applications")
    public ResponseEntity<List<ApplicationDTO>> findAll() {

        return new ResponseEntity<>(applicationService.findAll(), HttpStatus.OK);

    }

    @ApiOperation(value = "Retrieve application based on application id")
    @GetMapping(value = "/applications/{applicationId}")
    public ResponseEntity<ApplicationDTO> findApplicationById(
            @Valid
            @ApiParam(required = true, example = "1", value="Application Id")
            @PathVariable(value= APPLICATION_ID) Long applicationId) {

        return new ResponseEntity<>(applicationService.findApplicationById(applicationId), HttpStatus.OK);

    }

    @ApiOperation(value = "Retrieve all applications based on offer id")
    @GetMapping(value = "/offers/{offerId}/applications")
    public ResponseEntity<List<ApplicationDTO>> findByOffer(
            @Valid
            @ApiParam(required = true, example = "2", value="Offer Id")
            @PathVariable(value= OFFER_ID) Long offerId) {

            return new ResponseEntity<>(applicationService.findApplicationByOffer(offerId), HttpStatus.OK);

    }

    @ApiOperation(value = "Retrieve application based on offer id and application id")
    @GetMapping(value = "/offers/{offerId}/applications/{applicationId}")
    public ResponseEntity<ApplicationDTO> findByOfferAndApplication(
            @Valid
            @ApiParam(required = true, example = "2", value="Offer Id")
            @PathVariable(value= OFFER_ID) Long offerId,
            @Valid
            @ApiParam(required = true, example = "1", value="Application Id")
            @PathVariable(value= APPLICATION_ID) Long applicationId) {

        return new ResponseEntity<>(applicationService.findByOfferAndApplication(offerId, applicationId), HttpStatus.OK);

    }

    @ApiOperation(value = "Track number of applications")
    @GetMapping(value = "/applications/count")
    public ResponseEntity<?> findApplications(){
        Long count = applicationService.findApplications();
        if(null == count) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @ApiOperation(value = "Apply for an offer.")
    @PostMapping(value = "/offers/{offerId}/applications")
    public ResponseEntity<ApplicationDTO> apply(
            @Valid
            @ApiParam(required = true, example = "2", value="Offer Id")
            @PathVariable(value= OFFER_ID) Long offerId,
            @RequestBody @Valid ApplicationInputDTO application){

        return new ResponseEntity<>(applicationService.applyForOffer(offerId, application), HttpStatus.OK);
    }

    @ApiOperation(value = "Progress the status of an application.")
    @PatchMapping(value = "/applications/{applicationId}/status/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable(value = APPLICATION_ID) Long id, @PathVariable ApplicationStatus status) {
        applicationService.progressStatus(id, status);
        return ResponseEntity.noContent().build();
    }



}
