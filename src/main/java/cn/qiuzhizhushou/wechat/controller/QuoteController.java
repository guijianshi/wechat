package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.pojo.QuoteAuthorAndArticle;
import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.Quote;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        Quote quote = quoteService.findOneByRand();
        if (null == quote) {
            throw new BusinessException(EmBusinessError.QUOTE_NOT_EXIST_ERROR);
        }
        QuoteAuthorAndArticle quoteAuthorAndArticle = new QuoteAuthorAndArticle(quote);
        return JsonResponse.success(quoteAuthorAndArticle);
    }
}
