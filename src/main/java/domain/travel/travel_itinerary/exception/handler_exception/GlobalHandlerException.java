package domain.travel.travel_itinerary.exception.handler_exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import domain.travel.travel_itinerary.exception.BaseException;
import domain.travel.travel_itinerary.helper.base.dto.ErrorGlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGlobalResponse handleBaseException(BaseException ex, WebRequest request) {
        log.error("====================> {}", ex.getClass().getName());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    //    Handler of @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGlobalResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("====================> handlerMethodArgumentNotValidException");
        String errorMessage =
                ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(","));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, request);
    }

//    Handler of @PathVariable, @PathParams, @RequestHeader enum type
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGlobalResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        System.out.print("====================> handlerHttpMessageNotReadableException");
        String errorMessage = ex.getMessage().substring(ex.getMessage().lastIndexOf(":") + 1);
        errorMessage = "Invalid one of type: " + errorMessage;
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, request);
    }


    //    Handler of @PathVariable, @PathParams, @RequestHeader
    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGlobalResponse handleMethodValidationException(HandlerMethodValidationException ex, WebRequest request) {
        String message = ex.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request);
    }

    //    Handler of Authentication
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorGlobalResponse handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        String message = ex.getMessage();
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, message, request);
    }


    //    Function log of ErrorGlobalResponse
    private ErrorGlobalResponse buildErrorResponse(HttpStatus status, String message, WebRequest request) {
        ErrorGlobalResponse error = new ErrorGlobalResponse();
        error.setDate(new Date());
        error.setStatus(status.value());
        error.setError(status.getReasonPhrase());
        error.setPath(request.getDescription(false).replace("uri=", ""));
        error.setMessage(message);
        return error;
    }
}
