package themeansquare.service.internal;

import themeansquare.service.IRegistration;
import java.util.HashMap;

public class Registration implements IRegistration {

    public boolean validateInput(HashMap<String, String> params) {
        return true;
    }

    public boolean insertUser() {
        return true;
    }
}
