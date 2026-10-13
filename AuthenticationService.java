public class AuthenticationService {

    private User user;

    public AuthenticationService(User user) {
        this.user = user;
    }

    public boolean login(
            String username,
            String password
    ) {

        if (username == null ||
                password == null) {

            return false;
        }

        String enteredPasswordHash =
                PasswordUtil.hashPassword(
                        password
                );

        return username.equals(
                user.getUsername()
        ) &&
                enteredPasswordHash.equals(
                        user.getPasswordHash()
                );
    }
}