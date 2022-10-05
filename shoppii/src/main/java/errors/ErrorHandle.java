package errors;

public class ErrorHandle extends Error {
    String message;
    int statusCode;
    Exception e;

    public ErrorHandle(String message, int statusCode) {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorHandle(String message, int statusCode, Exception e) {
        super();
        this.message = message;
        this.statusCode = statusCode;
        this.e = e;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
