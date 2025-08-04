package domain.travel.travel_itinerary.helper.validators.uuid_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
@Target({PARAMETER,FIELD, TYPE_USE, TYPE_PARAMETER })
public @interface ValidUUID {

    String message() default "Invalid type of UUID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
