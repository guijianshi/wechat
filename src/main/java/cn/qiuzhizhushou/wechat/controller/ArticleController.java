package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午7:54
 */
@RestController
@RequestMapping("wechat/article")
public class ArticleController extends BaseController
{
    @Autowired
    ArticleService articleService;

    @RequestMapping("/list")
    private JsonResponse list(@RequestParam int page)
    {
        return JsonResponse.success(articleService.list());
    }

    @RequestMapping("/{id}")
    private JsonResponse find(@PathVariable int id)
    {
        return JsonResponse.success(articleService.find(id));
    }
}
