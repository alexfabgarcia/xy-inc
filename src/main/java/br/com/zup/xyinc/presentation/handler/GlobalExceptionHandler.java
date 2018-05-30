package br.com.zup.xyinc.presentation.handler;

import br.com.zup.xyinc.common.dto.ResponseErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

/**
 * {@link ControllerAdvice} responsável por manipular os erros globalmente.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String PONTO_VIRGULA_ESPACO = "; ";
    private static final String RESOURCE_NOT_FOUND_KEY = "xyinc.resource.not.found";

    @Autowired
    private MessageSource messageSource;

    /*
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ResponseErrorDto defaultErrorHandler(Exception e) throws Exception {
        ResponseStatus annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return new ResponseErrorDto(annotation.code(), annotation.reason());
        }

        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado.");
    }
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseErrorDto bindingError(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> messageSource.getMessage(fieldError.getDefaultMessage(), fieldError.getArguments(),
                        LocaleContextHolder.getLocale()))
                .collect(Collectors.joining(PONTO_VIRGULA_ESPACO));
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST, errorMsg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody ResponseErrorDto handleMissingParams(MissingServletRequestParameterException e) {
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public @ResponseBody ResponseErrorDto notFound() {
        return new ResponseErrorDto(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND_KEY);
    }

}
