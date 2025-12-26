package com.ecommarce.app.Contoller;

import com.ecommarce.app.dto.CustomerRequest;
import com.ecommarce.app.dto.CustomerResponse;
import com.ecommarce.app.entity.Customer;
import com.ecommarce.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@Validated @RequestBody CustomerRequest customer) {
        return ResponseEntity.ok().body(customerService.createCustomer(customer));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Validated @RequestBody CustomerRequest customerRequest){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomer());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity <Boolean> existsById(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomser(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }

}
