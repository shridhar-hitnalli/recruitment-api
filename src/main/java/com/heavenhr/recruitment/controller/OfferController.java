package com.heavenhr.recruitment.controller;

import com.heavenhr.recruitment.dto.OfferDTO;
import com.heavenhr.recruitment.dto.OfferInputDTO;
import com.heavenhr.recruitment.service.offer.OfferService;
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
@Api(tags = "Offer", value = "Offer")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful Read"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 409, message = "Conflict"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class OfferController {

    private static final String OFFER_ID = "offerId";

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ApiOperation(value = "Retrieve all offers")
    @GetMapping(value = "/offers")
    public ResponseEntity<List<OfferDTO>> findAll() {
        return new ResponseEntity<>(offerService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all offers based on offer id")
    @GetMapping(value = "/offers/{offerId}")
    public ResponseEntity<OfferDTO> findById(
            @Valid
            @ApiParam(required = true, example = "2", value="Offer Id")
            @PathVariable(value= OFFER_ID) Long offerId) {

        return new ResponseEntity<>(offerService.findById(offerId), HttpStatus.OK);

    }

    @ApiOperation(value = "Create offer")
    @PostMapping(value = "/offers")
    public ResponseEntity<OfferDTO> create(
            @ApiParam(required = true)
            @RequestBody @Valid OfferInputDTO offer){

        return new ResponseEntity<>(offerService.create(offer), HttpStatus.OK);
    }


}
