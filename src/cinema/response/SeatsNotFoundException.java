package cinema.response;

public class SeatsNotFoundException {

    private String error;

    public SeatsNotFoundException(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
