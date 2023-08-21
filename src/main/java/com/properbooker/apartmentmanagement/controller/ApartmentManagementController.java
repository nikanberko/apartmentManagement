package com.properbooker.apartmentmanagement.controller;


import com.properbooker.apartmentmanagement.message.ResponseMessage;
import com.properbooker.apartmentmanagement.model.AddApartmentDTO;
import com.properbooker.apartmentmanagement.model.Apartment;
import com.properbooker.apartmentmanagement.repository.ApartmentRepository;
import com.properbooker.apartmentmanagement.security.TokenProvider;
import com.properbooker.apartmentmanagement.service.ApartmentManagementService;
import com.properbooker.apartmentmanagement.utils.ApartmentMapper;
import io.swagger.annotations.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/apartments")
@Api(tags = "apartments")
@RequiredArgsConstructor
public class ApartmentManagementController {
    private final TokenProvider tokenProvider;
    private final ApartmentManagementService apartmentManagementService;

    private final ApartmentRepository apartmentRepository;

    @GetMapping("/getall")
    @ApiOperation(value = "${ApartmentController.getAllApartments}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public ResponseEntity<List<Apartment>> getAllApartments( HttpServletRequest request) throws RuntimeException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        }
        token = token.replace("Bearer ", "");
        String username = tokenProvider.getUsernameFromToken(token);
        List<Apartment> apartments = apartmentRepository.findAllByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(apartments);

    }

    @PostMapping("/add")
    @ApiOperation(value = "${ApartmentController.addApartment}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public ResponseEntity<ResponseMessage> addApartment(@ApiParam("Add apartment") @RequestBody @Valid AddApartmentDTO addApartmentDTO,
                                                        HttpServletRequest request
    ) throws RuntimeException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Missing credentials"));
        }
        token = token.replace("Bearer ", "");

        String username;

        try {
            username = tokenProvider.getUsernameFromToken(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Invalid user ID in token"));
        }
        String message = "";
        try {
            apartmentManagementService.addApartment(addApartmentDTO, username);
            message = "Apartment " + addApartmentDTO.getName() + " successfully added.";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Failed to add apartment.";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/getbyid{id}")
    @ApiOperation(value = "${ApartmentController.getAllApartments}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public Optional<Apartment> getApartmentById(@ApiParam("Apartment Id") @PathVariable Integer id) {
        return apartmentManagementService.getApartmentById(id);
    }

    @DeleteMapping("/delete{id}")
    @ApiOperation(value = "${ApartmentController.addApartment}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public void deleteApartment(@ApiParam("Add apartment") @PathVariable Integer id) {
        apartmentManagementService.deleteApartment(id);
    }
}
