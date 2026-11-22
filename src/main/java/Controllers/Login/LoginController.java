package Controllers.Login;

public class LoginController {
    public boolean checkUsernameAndPassword(String username, String password) {
        return username.equals("Admin") && password.equals("admin123");
    }
}
