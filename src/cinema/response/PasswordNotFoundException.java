package cinema.response;

public class PasswordNotFoundException {

    private String error;

    public PasswordNotFoundException(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
