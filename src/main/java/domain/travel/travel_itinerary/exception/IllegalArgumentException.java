package domain.travel.travel_itinerary.exception;

import java.awt.event.FocusEvent;

public class IllegalArgumentException extends BaseException {
    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message,  Throwable cause) {
        super(message, cause);
    }
}
