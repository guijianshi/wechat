package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.pojo.QuoteAuthorAndArticle;
import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.Quote;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午1:24
 */
@RestController
@RequestMapping("wechat/quote")
public class QuoteController extends BaseController
{
	private static long expire = 0;

	private static QuoteAuthorAndArticle quoteAuthorAndArticle;

	@Autowired
	QuoteService quoteService;

	@RequestMapping(value = "/{id}")
	public JsonResponse findById(@PathVariable int id) throws BusinessException
	{
		Quote quote = quoteService.findById(id);
		if (null == quote) {
			throw new BusinessException(EmBusinessError.QUOTE_NOT_EXIST_ERROR);
		}
		return JsonResponse.success(quote);
	}

	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public JsonResponse findOneByRand() throws BusinessException
	{
		long time = System.currentTimeMillis();
		if (expire > time) {
			return JsonResponse.success(quoteAuthorAndArticle);
		}
		Quote quote = quoteService.findOneByRand();
		if (null == quote) {
			throw new BusinessException(EmBusinessError.QUOTE_NOT_EXIST_ERROR);
		}
		quoteAuthorAndArticle = new QuoteAuthorAndArticle(quote);
		expire = time + 3600 * 1000;
		return JsonResponse.success(quoteAuthorAndArticle);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResponse list(@RequestParam(defaultValue = "30") int num)
	{
	    num = num > 50 || num < 5 ? 50 : num;
		java.util.Random random = new java.util.Random();
		int[] ids = new int[num];
		for (int i = 0; i < num; i++) {
			ids[i] = random.nextInt(7000);
		}
		return JsonResponse.success(quoteService.selectByIds(ids));
	}

	@RequestMapping(value = "/findByArticleId/{articleId}", method = RequestMethod.GET)
	public JsonResponse findByArticleId(@PathVariable int articleId) throws BusinessException
	{
		List<Quote> quotes = quoteService.findByArticleId(articleId);
		if (null == quotes) {
			throw new BusinessException(EmBusinessError.QUOTE_NOT_EXIST_ERROR);
		}
		return JsonResponse.success(quotes);
	}
}
