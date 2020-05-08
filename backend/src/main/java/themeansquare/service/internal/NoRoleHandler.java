package themeansquare.service.internal;

import themeansquare.repository.EmployeeRepository;
import themeansquare.service.IRoleHandler;

public class NoRoleHandler implements IRoleHandler {


    @Override
    public void setSuccessor(IRoleHandler role) {

    }

   
    public void setRepo(EmployeeRepository repo) {

    }

    @Override
    public String handle(int userId) {
       return "No role :(";
    }

    @Override
    public boolean validation(int userId) {
        
        return false;
    }
    
}