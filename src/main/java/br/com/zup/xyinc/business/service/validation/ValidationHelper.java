package br.com.zup.xyinc.business.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.HashSet;
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

    /**
     * Realiza a validação da entidade.
     * @param entity A entidade a ser validada.
     * @param <T> O tipo da entidade.
     * @throws ConstraintViolationException em caso de existir alguma violação.
     */
    public <T> void validate(final T entity) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> invalidValues = validator.validate(entity);

        if (!invalidValues.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(invalidValues));
        }
    }
}
