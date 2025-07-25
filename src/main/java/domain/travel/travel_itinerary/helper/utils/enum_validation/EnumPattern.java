package domain.travel.travel_itinerary.helper.utils.enum_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE})
@Constraint(validatedBy = EnumPatternValidator.class)
public @interface EnumPattern {
    String name();

    Class<? extends Enum<?>> enumClass();

    String message() default "Field {name} not match {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}