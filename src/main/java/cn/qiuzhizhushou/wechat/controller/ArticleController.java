package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.model.Article;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResponse list(@RequestParam(defaultValue = "10") int num)
    {
        java.util.Random random = new java.util.Random();
        int[] ids = new int[num];
        for (int i = 0; i < num; i++) {
			ids[i] = random.nextInt(5500);
        }
        return JsonResponse.success(articleService.selectByIds(ids));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResponse find(@PathVariable int id)
    {
        return JsonResponse.success(articleService.findById(id).formatFull());
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public JsonResponse search(@RequestParam String value)
    {
        List<Article> articles = articleService.search("%" + value + "%");
        return JsonResponse.success(articles);
    }

	@RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
	public JsonResponse selectByTagId(@PathVariable int tagId, @RequestParam(defaultValue = "1") int page)
	{
		int offset = this.getOffset(page);
		List<Article> articles = articleService.selectByTagId(tagId, offset);
		return JsonResponse.success(articles);
	}

	@RequestMapping(value = "/author/{authorId}", method = RequestMethod.GET)
	public JsonResponse selectByAuthorId(@PathVariable int authorId, @RequestParam(defaultValue = "1") int page)
	{
		int offset = this.getOffset(page);
		List<Article> articles = articleService.selectByAuthorId(authorId, offset);
		return JsonResponse.success(articles);
	}
}
