package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.response.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/12
 * Time: 下午7:51
 */
@RestController
@RequestMapping("wechat/sign")
public class SignController extends BaseController
{
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public JsonResponse signIn()
    {
        return JsonResponse.success("");
    }
}
