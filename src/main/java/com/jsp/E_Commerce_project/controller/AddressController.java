package com.jsp.E_Commerce_project.controller;


import com.jsp.E_Commerce_project.dto.address.CreateAddressRequestDto;
import com.jsp.E_Commerce_project.dto.address.CreateAddressResponseDto;
import com.jsp.E_Commerce_project.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{userId}/userId")
    public ResponseEntity<CreateAddressResponseDto> createAddress(@RequestBody CreateAddressRequestDto request, @PathVariable Long userId) {
       CreateAddressResponseDto addressdto =addressService.createAddress(request,userId);

        return ResponseEntity.status(HttpStatus.CREATED.value())
                              .body(addressdto);
    }


    @GetMapping("/{userId}/userId")
    public List<CreateAddressResponseDto> getMyAddresses(@PathVariable long userId) {
        return addressService.getMyAddresses(userId);
    }


    @PutMapping
    public CreateAddressResponseDto updateAddress(Long addressId, CreateAddressRequestDto request) {
        return null;
    }


    @DeleteMapping
    public ResponseEntity<String> deleteAddress(Long addressId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(addressService.deleteAddress(addressId));
    }


    @PatchMapping
    public CreateAddressResponseDto markDefaultAddress(Long addressId) {

        return null;
    }
}
