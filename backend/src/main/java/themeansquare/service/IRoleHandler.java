package themeansquare.service;

import org.springframework.data.repository.CrudRepository;

public interface IRoleHandler {
    public void setSuccessor(IRoleHandler role);
    
    public String handle(int userId);
    public boolean validation(int userId);
}