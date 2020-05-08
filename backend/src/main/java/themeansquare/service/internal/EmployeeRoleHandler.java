package themeansquare.service.internal;

import java.util.Iterator;

import themeansquare.model.Employee;
import themeansquare.repository.EmployeeRepository;
import themeansquare.service.IRoleHandler;

public class EmployeeRoleHandler implements IRoleHandler {

    private EmployeeRepository employeeRepository;
    private IRoleHandler successor;

    @Override
    public void setSuccessor(IRoleHandler role) {
        this.successor = role;

    }

   
    public void setRepo(EmployeeRepository repo) {
        this.employeeRepository = repo;

    }

    @Override
    public String handle(int userId) {
        if (validation(userId)) {
            return "Employee";
        }
        return this.successor.handle(userId);
    }

    @Override
    public boolean validation(int userId) {
        Iterable<Employee> empaItr = employeeRepository.findAll();

        Iterator empIt = empaItr.iterator();
        
        while (empIt.hasNext()) {
            Employee employee = (Employee) empIt.next();
            
            if (employee.getUserId().getId() == userId) {
                return true;
            }
        }
        return false;
    }
    
}