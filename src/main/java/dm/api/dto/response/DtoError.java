package dm.api.dto.response;


public class DtoError {
    private String message;

    public DtoError() {
    }

    public DtoError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DtoError{" +
                "message='" + message + '\'' +
                '}';
    }
}
