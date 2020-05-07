package themeansquare.service.internal;

import java.util.Iterator;

import themeansquare.model.Customer;
import themeansquare.repository.CustomerRepository;
import themeansquare.service.IRoleHandler;

public class CustomerRoleHandler implements IRoleHandler {

    private CustomerRepository customerRepository;
    private IRoleHandler successor;

    @Override
    public void setSuccessor(IRoleHandler role) {
        this.successor = role;

    }

   
    public void setRepo(CustomerRepository repo) {
        this.customerRepository = repo;

    }

    @Override
    public String handle(int userId) {
        if (validation(userId)) {
            return "Customer";
        }
        return this.successor.handle(userId);
    }

    @Override
    public boolean validation(int userId) {
        Iterable<Customer> custItr = customerRepository.findAll();

        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            Customer customer = (Customer) custIt.next();
            
            if (customer.getUserId().getId() == userId) {
                return true;
            }
        }
        return false;
    }
    
}