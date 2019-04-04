package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.enums.CollectionTypeEnum;
import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.ArticleCollection;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.ArticleCollectionService;
import cn.qiuzhizhushou.wechat.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午11:26
 */
@RestController
@RequestMapping("wechat/collection")
public class CollectionController extends BaseController
{
	@Autowired
	ArticleCollectionService articleCollectionService;


	/**
	 * 添加收藏
	 *
	 * @param articleId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResponse add(@RequestParam() int articleId) throws BusinessException
	{
		return this.add("article", articleId);
	}

	/**
	 * 取消收藏
	 *
	 * @param articleId 文章号
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public JsonResponse cancel(@RequestParam() int articleId) throws BusinessException
	{
		int userId = Token.getUserId();

		ArticleCollection collection = articleCollectionService.findByUidAndArticleId(userId, articleId);
		if (null == collection || !collection.isCollected()) {
			throw new BusinessException(EmBusinessError.COLLECTION_NOT_EXISTED_ERROR);
		}

		collection.setCancel();
		if (!articleCollectionService.update(collection)) {
			throw new BusinessException(EmBusinessError.ADD_COLLECTION_ERROR);
		}
		return JsonResponse.success(null);
	}

	/**
	 * 是否收藏文章
	 *
	 * @param articleId 文章号
	 * @return
	 */
	@RequestMapping(value = "/isCollected", method = RequestMethod.GET)
	public JsonResponse isCollected(@RequestParam() int articleId)
	{
		int userId = Token.getUserId();

		boolean isCollected = articleCollectionService.isCollected(userId, articleId);
		Map<String, Object> data = new HashMap<>();
		data.put("isCollected", isCollected);
		return JsonResponse.success(data);
	}

	/**
	 * 收藏列表
	 *
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResponse list(@RequestParam(defaultValue = "1") int page)
	{
		int userId = Token.getUserId();
		int offset = getOffset(page);
		return JsonResponse.success(articleCollectionService.articleList(userId, offset));
	}

	/**
	 * 添加收藏
	 *
	 * @param typeStr  article, quote
	 * @param targetId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/add/{typeStr}", method = RequestMethod.GET)
	public JsonResponse add(@PathVariable String typeStr, @RequestParam int targetId) throws BusinessException
	{
		if (!"article".equals(typeStr) && !"quote".equals(typeStr)) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
		}
		int type;
		switch (typeStr) {
			case "article":
				type = CollectionTypeEnum.ARTICLE.getCode();
				break;
			case "quote":
				type = CollectionTypeEnum.QUOTE.getCode();
				break;
			default:
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);

		}
		int userId = Token.getUserId();

		ArticleCollection collection = articleCollectionService.findByUidAndTypeAndTargetId(userId, type, targetId);
		boolean flag;
		if (null != collection) {
			if (collection.isCollected()) {
				throw new BusinessException(EmBusinessError.ALREADY_COLLECTED_ERROR);
			} else {
				collection.setActive();
				flag = articleCollectionService.update(collection);
			}
		} else {
			collection = new ArticleCollection(userId, targetId, CollectionTypeEnum.ARTICLE.getCode());
			flag = articleCollectionService.save(collection);
		}

		if (!flag) {
			throw new BusinessException(EmBusinessError.ADD_COLLECTION_ERROR);
		}
		return JsonResponse.success(null);
	}
}
