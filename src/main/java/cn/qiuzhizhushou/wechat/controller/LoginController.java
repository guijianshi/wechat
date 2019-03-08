package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.MiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午7:36
 */
@RestController
public class LoginController extends BaseController
{
    @Autowired
    MiniProgramService miniProgramService;

    @RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
    public JsonResponse wxlogin(@RequestParam String code) throws BusinessException
    {
        return JsonResponse.success(miniProgramService.wxlogin(code));
    }
}
