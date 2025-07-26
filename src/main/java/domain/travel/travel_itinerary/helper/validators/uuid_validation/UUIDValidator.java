package domain.travel.travel_itinerary.helper.validators.uuid_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;


public class UUIDValidator implements ConstraintValidator<ValidUUID, UUID> {

    @Override
    public void initialize(ValidUUID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {
        if (id == null || id.toString().isEmpty()) return false;
        try {
            UUID.fromString(id.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
