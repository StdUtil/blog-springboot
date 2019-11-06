package net.stackoverflow.blog.web.controller;

import net.stackoverflow.blog.common.Result;
import net.stackoverflow.blog.exception.BusinessException;
import net.stackoverflow.blog.exception.ServerException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理控制器
 *
 * @author 凉衫薄
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * 处理业务异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity handleBusinessException(BusinessException e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            Result result = new Result();
            result.setStatus(Result.FAILURE);
            result.setMessage(e.getMessage());
            result.setData(e.getData());
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * 未授权错误处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        if (!isAjaxRequest(request)) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("/error/unauthorized");
            return mv;
        } else {
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * 处理未认证错误
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ModelAndView handleUnauthenticatedException(UnauthenticatedException e, HttpServletRequest request) {
        if (!isAjaxRequest(request)) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("/login");
            return mv;
        } else {
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * 处理AJAX请求500错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity handleException(Exception e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            Result result = new Result();
            result.setStatus(Result.SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(e.getStackTrace());
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            throw new ServerException(e.getClass().getName());
        }
    }

    /**
     * 判断请求是否为ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        if ((request.getHeader("accept") != null && request.getHeader("accept").contains("application/json")) || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {
            return true;
        } else {
            return false;
        }
    }
}
