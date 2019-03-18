package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.Sign;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.SignService;
import cn.qiuzhizhushou.wechat.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    SignService signService;

    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public JsonResponse signIn() throws BusinessException
    {
        int userId = Token.getUserId();

        if (null != signService.findByUidToday(userId)) {
            throw new BusinessException(EmBusinessError.ALREADY_SIGNED_ERROR);
        }
        Sign sign = new Sign(userId);
        if (!signService.save(sign)) {
            return JsonResponse.create(1, "签到失败");
        }
        return JsonResponse.success("签到成功");
    }

    @RequestMapping(value = "isSigned", method = RequestMethod.GET)
    public JsonResponse isSign()
    {
        int userId = Token.getUserId();
        Map<String, Object> data = new HashMap<>();
        boolean isSigned = signService.isSigned(userId);
        data.put("isSigned", isSigned);
        return JsonResponse.success(data);
    }
}