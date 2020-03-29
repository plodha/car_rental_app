package themeansquare.service.internal;

import java.util.HashMap;
import themeansquare.service.IRegistration;

public class Registration implements IRegistration {

    public boolean validateInput(HashMap<String, String> params) {
        return true;
    }

    public boolean insertUser() {
        return true;
    }
}
