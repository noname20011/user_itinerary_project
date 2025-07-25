package domain.travel.travel_itinerary.helper.utils.uuid_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.executable.ValidateOnExecution;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
@Target({PARAMETER,FIELD, TYPE_USE, TYPE_PARAMETER })
public @interface ValidUUID {

    String message() default "Invalid UUID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
