package codehumane.customerprofile;

import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrar {

    private final CustomerRepository customerRepository;
    private final Sender sender;

    CustomerRegistrar(CustomerRepository customerRepository, Sender sender) {
        this.customerRepository = customerRepository;
        this.sender = sender;
    }

    Customer register(Customer customer) {
        customerRepository
                .findByName(customer.getName())
                .ifPresent((x) -> new RuntimeException(customer.getName() + " already exists"));

        customerRepository.save(customer);
        sender.send(customer.getEmail());
        return customer;
    }
}
