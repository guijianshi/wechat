package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.model.Tag;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.TagService;
import cn.qiuzhizhushou.wechat.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/25
 * Time: 上午11:45
 */
@RestController
@RequestMapping("wechat/tag")
public class TagController extends BaseController
{
	@Autowired
	TagService tagService;

	@RequestMapping(value = "hot", method = RequestMethod.GET)
	public JsonResponse hotTag()
	{
		List<Tag> tags= tagService.getHotTag();
		return JsonResponse.success(tags);
	}

	// 用户喜欢的标签 todo
	public JsonResponse index()
	{
		int userId = Token.getUserId();

		return JsonResponse.success(null);
	}
}
