package br.com.zup.xyinc.business.service.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utilitário para valiação a partir de Bean Validation.
 */
@Component
public class ValidationHelper {

    private final ValidatorFactory factory;

    @Autowired
    public ValidationHelper(MessageInterpolator messageInterpolator) {
        factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(messageInterpolator)
                .buildValidatorFactory();
    }

    public <T> void validate(final T entity) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> invalidValues = validator.validate(entity);

        if (!invalidValues.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(invalidValues));
        }
    }
}
