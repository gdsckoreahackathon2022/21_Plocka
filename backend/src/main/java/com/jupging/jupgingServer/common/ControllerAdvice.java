package com.jupging.jupgingServer.common;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.sql.SQLException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.jupging.jupgingServer.common.BaseResponseStatus.*;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> handleBaseException(BaseException e)
    {
        return new BaseResponse<>(e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<BaseResponseStatus> handleMethodArgumentNotValidException()
    {
        // valid 에러
        return new BaseResponse<>(REQUEST_ERROR);
    }

    @ExceptionHandler(SQLException.class)
    public BaseResponse<BaseResponseStatus> handleSQLException()
    {
        return new BaseResponse<>(DATABASE_ERROR);
    }

    @ExceptionHandler(GoogleJsonResponseException.class)
    public BaseResponse<BaseResponseStatus> handleGoogleJsonResponseError()
    {
        return new BaseResponse<>(GCS_ERROR);
    }

    @ExceptionHandler(ServletException.class)
    public BaseResponse<BaseResponseStatus> handleServletExceptionError()
    {
        return new BaseResponse<>(FILE_EXTENSION_ERROR);
    }
}
