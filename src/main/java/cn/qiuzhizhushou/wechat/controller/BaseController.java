package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 上午10:23
 */
public class BaseController
{
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex)
    {
        int code;
        String message;
        if (ex instanceof BusinessException) {
            code = ((BusinessException) ex).getErrCode();
            message = ((BusinessException) ex).getErrMsg();
        } else {
            code = 50000;
            message = ex.getMessage();
        }
        return JsonResponse.create(code, message);
    }
}
