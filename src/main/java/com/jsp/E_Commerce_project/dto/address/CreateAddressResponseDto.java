package com.jsp.E_Commerce_project.dto.address;


import lombok.Data;

@Data
public class CreateAddressResponseDto {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private boolean isDefault;
}
