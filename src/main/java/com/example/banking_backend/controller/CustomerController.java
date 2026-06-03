package com.example.banking_backend.controller;

import com.example.banking_backend.dto.CustomerRegistrationDto;
import com.example.banking_backend.dto.CustomerResponseDto;
import com.example.banking_backend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDto> registerCustomer(@Valid @RequestBody CustomerRegistrationDto registrationDto) {
        CustomerResponseDto response = customerService.registerCustomer(registrationDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRegistrationDto updateDto) {
        CustomerResponseDto response = customerService.updateCustomerProfile(id, updateDto);
        return ResponseEntity.ok(response);
    }
}
