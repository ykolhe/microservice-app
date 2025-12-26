package com.ecommarce.app.service;

import com.ecommarce.app.dto.CustomerRequest;

import com.ecommarce.app.dto.CustomerResponse;
import com.ecommarce.app.entity.Customer;
import com.ecommarce.app.exception.CustomerNotFoundException;
import com.ecommarce.app.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customer) {
        var customerDetail = customerRepo.save(customerMapper.toEntity(customer));
        return customerDetail.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer =customerRepo.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Can not update Customer as no Customer found for provided id "+customerRequest.id())
        ));
        updateCustomerDetail(customer,customerRequest);
        customerRepo.save(customer);
    }

    private void updateCustomerDetail(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address()!=null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> getCustomer() {
        List<Customer> all = customerRepo.findAll();
        return all.stream().map(customerMapper::toDto).toList();
    }

    public Boolean existsById(String customerId) {
        return customerRepo.existsById(customerId);
    }


    public CustomerResponse getCustomerById(String customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Can not update Customer as no Customer found for provided id " + customerId)
        ));
        return customerMapper.toDto(customer);
    }

    public void deleteCustomerById(String customerId) {
        customerRepo.deleteById(customerId);
    }
}
