package com.hongdun.exception;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.hongdun.entity.Response;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


/**
 * Created on 2019/2/28.
 *
 * @author zhang
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response runtimeExceptionHandler(RuntimeException ex) {
        logger.error("运行期异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(1000, null);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Response numPointerExceptionHandler(NullPointerException ex) {
        logger.error("空指针异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(1001, null);
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public Response classCastExceptionHandler(ClassCastException ex) {
        logger.error("类型转换异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(1002, null);
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Response iOExceptionHandler(IOException ex) {
        logger.error("IO异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(1003, null);
    }

    /**
     * 未知方法异常
     */

    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public Response noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        logger.error("未知方法异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(1004, null);
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Response indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        logger.error("数组下标越界异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(1005, null);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public Response requestNotReadable(HttpMessageNotReadableException ex) {
        logger.error("400异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(400, null);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Response requestMissingServletRequest(MissingServletRequestParameterException ex) {
        logger.error("400异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(400, null);
    }

    /**
     * 405错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Response request405(HttpRequestMethodNotSupportedException ex) {
        logger.error("405异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(405, null);
    }

    /**
     * 406错误
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public Response request406(HttpMediaTypeNotAcceptableException ex) {
        logger.error("406异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(406, null);
    }

    /**
     * 500错误
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public Response server500(RuntimeException ex) {
        logger.error("500异常:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(406, null);
    }

    /**
     * 500错误
     */
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    @ResponseBody
    public Response maxUploadSizeExceeded(RuntimeException ex) {
        logger.error("超出文件最大尺寸:", ex);
        logger.error(getErrorDetail(ex));
        return Response.retParam(2001, null);
    }

    /**
     * 参数错误
     */
    @ExceptionHandler({ConstraintViolationException.class, TypeMismatchException.class})
    @ResponseBody
    public Response serverValid(ConstraintViolationException constraintViolationException) {
        logger.error("参数匹配异常:", constraintViolationException);
        logger.error(getErrorDetail(constraintViolationException));
        Set<ConstraintViolation<?>> s = constraintViolationException.getConstraintViolations();
        Iterator<ConstraintViolation<?>> it = s.iterator();
        // 只返回第一个验证失败的
        if (it.hasNext()) {
            ConstraintViolationImpl constraintViolation = (ConstraintViolationImpl) it.next();
            String message = constraintViolation.getMessage();
            return Response.retParam(6, message);
        } else {
            return Response.retParam(6, null);
        }

    }


    private String getErrorDetail(Exception ex) {
        try {
            StackTraceElement stackTraceElement = ex.getStackTrace()[0];
            String fileName = stackTraceElement.getFileName();
            String method = stackTraceElement.getMethodName();
            Integer line = stackTraceElement.getLineNumber();
            logger.info("File=" + fileName);
            logger.info("Method=" + method);
            logger.info("Line=" + line);
            return fileName + "->" + method + "->" + line;
        } catch (Exception e) {
            return "";
        }

    }
}
