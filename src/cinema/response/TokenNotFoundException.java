package cinema.response;

public class TokenNotFoundException {

    private String error;

    public TokenNotFoundException(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
