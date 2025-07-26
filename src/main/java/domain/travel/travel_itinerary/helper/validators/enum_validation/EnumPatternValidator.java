package domain.travel.travel_itinerary.helper.validators.enum_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
    private Pattern pattern;

    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        try {
            final String regex = buildPatternFromEnum(constraintAnnotation.enumClass());
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid regexp: ", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext constraint) {
        if (value == null) return true;
        Matcher matcher = pattern.matcher(value.name());
        return matcher.matches();
    }

    public String buildPatternFromEnum(Class<? extends Enum<?>> enumClass) {
        return Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining("|"));
    }
}
