package codehumane.customerprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRegistrar {

    private final CustomerRepository customerRespository;

    CustomerRegistrar(CustomerRepository customerRespository) {
        this.customerRespository = customerRespository;
    }

    Customer register(Customer customer) {
        customerRespository
                .findByName(customer.getName())
                .ifPresent((x) -> new RuntimeException(customer.getName() + " already exists"));

        customerRespository.save(customer);
        return customer;
    }
}
