package com.jsp.E_Commerce_project.service;

import com.jsp.E_Commerce_project.dto.address.CreateAddressRequestDto;
import com.jsp.E_Commerce_project.dto.address.CreateAddressResponseDto;

import java.util.List;

public interface AddressService {
    CreateAddressResponseDto createAddress(CreateAddressRequestDto request, Long userId);

    List<CreateAddressResponseDto> getMyAddresses(long userId);

    CreateAddressResponseDto updateAddress(Long addressId, CreateAddressRequestDto request);

    String deleteAddress(Long addressId);

    CreateAddressResponseDto markDefaultAddress(Long addressId);
}
