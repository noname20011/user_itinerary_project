package domain.travel.travel_itinerary.exception;

public class DisabledException extends BaseException{
    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledException(String message) {
        super(message);
    }
}
