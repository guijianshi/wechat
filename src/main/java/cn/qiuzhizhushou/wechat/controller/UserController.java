package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.User;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.MiniProgramService;
import cn.qiuzhizhushou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/11
 * Time: 上午10:57
 */
@RestController
@RequestMapping("wechat/user")
public class UserController extends BaseController
{
    @Autowired
    UserService userService;

    @Autowired
    MiniProgramService miniProgramService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public JsonResponse getUserInfo()
    {

        return JsonResponse.success(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResponse find(@PathVariable int id)
    {
        return JsonResponse.success(userService.find(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse save(@RequestBody User user) throws BusinessException
    {
        User exist = userService.findIdByOpenid(user.getOpenid());
        if (null != exist) {
            throw new BusinessException(EmBusinessError.USER_EXISTED_ERROR);
        }
        return JsonResponse.success(userService.insert(user));
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public JsonResponse getUser(@RequestParam String data, @RequestParam String key, @RequestParam String iv) throws BusinessException
    {
        return JsonResponse.success(miniProgramService.getUserInfo(data, key, iv));
    }

    @RequestMapping(value = "/setRawData", method = RequestMethod.GET)
    public JsonResponse setRawData(@RequestParam String rawData, @RequestParam String signature)
    {
        return JsonResponse.success(miniProgramService.checkRawData(rawData, signature));
    }
}
