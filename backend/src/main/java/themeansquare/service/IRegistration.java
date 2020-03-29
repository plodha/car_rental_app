import java.util.HashMap;

public interface IRegistration {

    public boolean validateInput(HashMap<String, String> params);
    public boolean insertUser();
}