package themeansquare.service;

import themeansquare.model.User;

public interface IUser {
    public String isValidCredentials(User user);
}