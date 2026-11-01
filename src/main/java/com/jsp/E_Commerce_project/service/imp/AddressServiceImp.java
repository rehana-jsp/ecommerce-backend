package com.jsp.E_Commerce_project.service.imp;

import com.jsp.E_Commerce_project.dto.address.CreateAddressRequestDto;
import com.jsp.E_Commerce_project.dto.address.CreateAddressResponseDto;
import com.jsp.E_Commerce_project.exception.UserNotFound;
import com.jsp.E_Commerce_project.model.Address;
import com.jsp.E_Commerce_project.model.User;
import com.jsp.E_Commerce_project.repository.AddressRepository;
import com.jsp.E_Commerce_project.repository.UserRepository;
import com.jsp.E_Commerce_project.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public CreateAddressResponseDto createAddress(CreateAddressRequestDto request, Long userId) {
        //1.find the user
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFound("User Not Found with id: " + userId)
        );
        //create address
        Address address = objectMapper.convertValue(request, Address.class);
        //setting user into address
        address.setUser(user);

        if(user.getAddress()==null){
            address.setDefault(true);
        }

        address = addressRepository.save(address);

        return objectMapper.convertValue(address, CreateAddressResponseDto.class);
    }

    @Override
    public List<CreateAddressResponseDto> getMyAddresses(long userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(address -> convertToDto(address))
                .toList();
    }

    @Override
    public CreateAddressResponseDto updateAddress(Long addressId, CreateAddressRequestDto request) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                () -> new UserNotFound("Address Not Found with id: " + addressId)
        );
        Address addressTobeUpdated = objectMapper.convertValue(request, Address.class);
        addressTobeUpdated.setId(addressId);
        addressTobeUpdated.setUser(address.getUser());

        return objectMapper.convertValue(addressRepository.save(addressTobeUpdated), CreateAddressResponseDto.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                () -> new UserNotFound("Address Not Found with id: " + addressId)
        );

//        User user=address.getUser();
//        user.getAddress().removeIf(add -> add.getId()==addressId);
//        userRepository.save(address.getUser());
        addressRepository.delete(address);

        return "address has been deleted";
    }

    @Override
    public CreateAddressResponseDto markDefaultAddress(Long addressId) {

        Address currentDefaultAddress = addressRepository.findById(addressId).orElseThrow(
                () -> new UserNotFound("Address Not Found with id: " + addressId)
        );

            Address previousDefaultAddress = addressRepository.findByUserIdAndIsDefaultTrue(
                    currentDefaultAddress.getUser().getId()
            ).orElseThrow(
                    () -> new UserNotFound("User Not Found with id: " + addressId)
            );
            previousDefaultAddress.setDefault(false);
            addressRepository.save(previousDefaultAddress);

        currentDefaultAddress.setDefault(true);
        addressRepository.save(currentDefaultAddress);

        return objectMapper.convertValue(currentDefaultAddress, CreateAddressResponseDto.class);
    }


    public CreateAddressResponseDto convertToDto(Address address) {
        return objectMapper.convertValue(address, CreateAddressResponseDto.class);
    }

}




