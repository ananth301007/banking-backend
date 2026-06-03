package com.example.banking_backend.service;

import com.example.banking_backend.dto.CustomerRegistrationDto;
import com.example.banking_backend.dto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto registerCustomer(CustomerRegistrationDto registrationDto);
    CustomerResponseDto getCustomerById(Long id);
    CustomerResponseDto updateCustomerProfile(Long id, CustomerRegistrationDto updateDto);
}