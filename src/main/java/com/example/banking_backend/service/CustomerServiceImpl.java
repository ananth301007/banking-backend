package com.example.banking_backend.service;

import com.example.banking_backend.Exception.CustomerAlreadyExistsException;
import com.example.banking_backend.Exception.CustomerNotFoundException;
import com.example.banking_backend.dto.CustomerRegistrationDto;
import com.example.banking_backend.dto.CustomerResponseDto;
import com.example.banking_backend.model.Customer;
import com.example.banking_backend.model.KycDocument;
import com.example.banking_backend.repository.CustomerRepository;
import com.example.banking_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponseDto registerCustomer(CustomerRegistrationDto dto) {
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new CustomerAlreadyExistsException("A customer with email " + dto.getEmail() + " already exists.");
        }
        if (customerRepository.existsByPhone(dto.getPhone())) {
            throw new CustomerAlreadyExistsException("A customer with phone " + dto.getPhone() + " already exists.");
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        KycDocument kyc = new KycDocument();
        kyc.setIdentityType(dto.getIdentityType());
        kyc.setIdentityNumber(dto.getIdentityNumber());
        kyc.setStatus("PENDING");

        // Use the safe helper pattern to bind bidirectional elements
        customer.addKycDocument(kyc);

        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponseDto(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer record not found for ID: " + id));
        return mapToResponseDto(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDto updateCustomerProfile(Long id, CustomerRegistrationDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer profile update failed. Invalid ID: " + id));

        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToResponseDto(updatedCustomer);
    }

    private CustomerResponseDto mapToResponseDto(Customer customer) {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(customer.getId());
        responseDto.setName(customer.getName());
        responseDto.setEmail(customer.getEmail());
        responseDto.setPhone(customer.getPhone());

        if (customer.getKycDocuments() != null && !customer.getKycDocuments().isEmpty()) {
            responseDto.setKycStatus(customer.getKycDocuments().get(0).getStatus());
        } else {
            responseDto.setKycStatus("UNAVAILABLE");
        }
        return responseDto;
    }
}
