package domain.travel.travel_itinerary.exception;

public class BaseException extends RuntimeException {
    BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    BaseException(String message) {
        super(message);
    }
}
