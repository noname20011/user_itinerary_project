package domain.travel.travel_itinerary.exception;

public class UploadFileFailException extends BaseException {
    public UploadFileFailException(String message, Throwable cause) {
        super(message,  cause);
    }

    public UploadFileFailException(String message) {
        super(message);
    }
}
