package themeansquare.service;

public interface IRegistration {

    public String isValidParams();
    public boolean insertUser();
    public boolean isValidEmail();
    public boolean isUserNameTaken();
}