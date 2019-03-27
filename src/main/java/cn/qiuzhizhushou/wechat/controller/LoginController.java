package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.model.User;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.MiniProgramService;
import cn.qiuzhizhushou.wechat.service.UserService;
import cn.qiuzhizhushou.wechat.util.JWTUtil;
import cn.qiuzhizhushou.wechat.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午7:36
 */
@RestController
@RequestMapping("wechat/login")
public class LoginController extends BaseController
{
	@Autowired
	MiniProgramService miniProgramService;

	@Autowired
	UserService userService;

	@Autowired
	JWTUtil jwtUtil;

	@RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
	public JsonResponse wxlogin(@RequestParam String code, @RequestParam String rawData, @RequestParam String signature) throws BusinessException
	{
		Map<String, String> sessionAndOpenid = miniProgramService.wxlogin(code);
		String sessionKey = sessionAndOpenid.get("session_key");
		String openid = sessionAndOpenid.get("openid");
		User user = miniProgramService.getUserInfo(rawData, sessionKey, signature, openid);
		userService.saveOrUpdate(user);
		String token = jwtUtil.createToken(Token.createToken(user));
		Map<String, Object> data = new HashMap<>();
		data.put("token", token);
		return JsonResponse.success(data);
	}

	// 校验token是否有效(实际逻辑在前置过滤器当中)
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public JsonResponse check()
	{
		return JsonResponse.success(null);
	}

	// 登出
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public JsonResponse logout()
	{
		return JsonResponse.success(null);
	}
}
