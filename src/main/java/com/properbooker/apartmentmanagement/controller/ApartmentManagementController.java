package com.properbooker.apartmentmanagement.controller;


import com.properbooker.apartmentmanagement.model.AddApartmentDTO;
import com.properbooker.apartmentmanagement.model.Apartment;
import com.properbooker.apartmentmanagement.service.ApartmentManagementService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apartments")
@Api(tags = "apartments")
@RequiredArgsConstructor
public class ApartmentManagementController {

    private final ApartmentManagementService apartmentManagementService;

    @GetMapping("/getall")
    @ApiOperation(value = "${ApartmentController.getAllApartments}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public List<Apartment> getAllApartments() {
        return apartmentManagementService.getAllApartments();
    }

    @PostMapping("/add")
    @ApiOperation(value = "${ApartmentController.addApartment}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public Apartment addApartment(@ApiParam("Add apartment") @RequestBody AddApartmentDTO addApartmentDTO) {
        return apartmentManagementService.addApartment(addApartmentDTO);
    }

    @GetMapping("/getbyid")
    @ApiOperation(value = "${ApartmentController.getAllApartments}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public Optional<Apartment> getApartmentById(@ApiParam("Apartment Id") @PathVariable Integer id) {
        return apartmentManagementService.getApartmentbyId(id);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "${ApartmentController.addApartment}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public void deleteApartment(@ApiParam("Add apartment") @PathVariable Integer id) {
        apartmentManagementService.deleteApartment(id);
    }



}
