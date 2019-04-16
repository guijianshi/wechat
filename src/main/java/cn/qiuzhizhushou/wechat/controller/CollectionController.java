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
	 * 取消收藏
	 *
	 * @param articleId 文章号
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/{typeStr}/cancel", method = RequestMethod.POST)
	public JsonResponse cancel(@PathVariable String typeStr, @RequestParam() int targetId) throws BusinessException
	{
		int userId = Token.getUserId();
		int type = getType(typeStr);
		ArticleCollection collection = articleCollectionService.findByUidAndTypeAndTargetId(userId, type, targetId);
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
	 * @param typeStr 类型
	 * @param targetId 文章号
	 * @return
	 */
	@RequestMapping(value = "/{typeStr}/isCollected", method = RequestMethod.GET)
	public JsonResponse isCollected(@PathVariable String typeStr, @RequestParam() int targetId) throws BusinessException
	{
		int userId = Token.getUserId();
		int type = getType(typeStr);
		boolean isCollected = articleCollectionService.isCollected(userId, type, targetId);
		Map<String, Object> data = new HashMap<>();
		data.put("isCollected", isCollected);
		return JsonResponse.success(data);
	}

	/**
	 * 收藏列表
	 *
	 * @param page 页码
	 * @return
	 */
	@RequestMapping(value = "/{typeStr}/list", method = RequestMethod.GET)
	public JsonResponse list(@PathVariable String typeStr, @RequestParam(defaultValue = "1") int page) throws BusinessException
	{
		int type = getType(typeStr);
		int userId = Token.getUserId();
		int offset = getOffset(page);
		if (type == CollectionTypeEnum.ARTICLE.getCode()) {
			return JsonResponse.success(articleCollectionService.articleList(userId, offset));
		} else {
			return JsonResponse.success(articleCollectionService.quoteList(userId, offset));
		}
	}

	/**
	 * 添加收藏
	 *
	 * @param typeStr  article, quote
	 * @param targetId 目标主键
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/{typeStr}/add", method = RequestMethod.POST)
	public JsonResponse add(@PathVariable String typeStr, @RequestParam int targetId) throws BusinessException
	{
		int type = getType(typeStr);
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
			collection = new ArticleCollection(userId, targetId, type);
			flag = articleCollectionService.save(collection);
		}

		if (!flag) {
			throw new BusinessException(EmBusinessError.ADD_COLLECTION_ERROR);
		}
		return JsonResponse.success(null);
	}

	/**
	 * 获取请求收藏类型
	 *
	 * @param typeStr
	 * @return
	 * @throws BusinessException
	 */
	private int getType(String typeStr) throws BusinessException
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
		return type;
	}
}
