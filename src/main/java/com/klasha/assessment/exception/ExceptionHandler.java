package com.klasha.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @org.springframework.web.bind.annotation.ExceptionHandler(AsyncRequestTimeoutException.class)
    public Map<String, String> errorHandler(AsyncRequestTimeoutException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "Unable to call the endpoint");
        errorRes.put("responseCode", "E99");

        return errorRes;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public Map<String, String> nullPointer(NullPointerException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "one of the request value is empty or you are sending a bad request");
        errorRes.put("responseCode", "E98");

        return errorRes;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public Map<String, String> runTime(RuntimeException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "one of the request value is empty or you are sending a bad request");
        errorRes.put("responseCode", "E97");

        return errorRes;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND )
    @ResponseBody
    public Map<String, String> handleNotFoundError(NoHandlerFoundException ex) {
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "Path does not exist");
        errorRes.put("responseCode", "404");
        return errorRes;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Map<String, String> missParameter(MissingServletRequestParameterException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "Bad Request");
        errorRes.put("responseCode", "E96");

        return errorRes;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Map<String, String> badReq(IllegalArgumentException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "Bad Request");
        errorRes.put("responseCode", "500");

        return errorRes;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public Map<String, String> arrayOutBound(ArrayIndexOutOfBoundsException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("responseMessage", "Array out of bound");
        errorRes.put("responseCode", "E00");

        return errorRes;
    }

}

