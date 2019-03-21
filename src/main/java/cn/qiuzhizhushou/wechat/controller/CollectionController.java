package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.Collection;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.CollectionService;
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
 * Date: 19/3/21
 * Time: 上午11:26
 */
@RestController
@RequestMapping("wechat/collection")
public class CollectionController extends BaseController
{
    @Autowired
    CollectionService collectionService;


    /**
     * 添加收藏
     * @param userId
     * @param articleId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(@RequestParam() int userId, @RequestParam() int articleId) throws BusinessException
    {
        if (collectionService.isCollected(userId, articleId)) {
            throw new BusinessException(EmBusinessError.ALREADY_COLLECTED_ERROR);
        }
        Collection collection = new Collection(userId, articleId);
        if (!collectionService.save(collection)) {
            throw new BusinessException(EmBusinessError.ADD_COLLECTION_ERROR);
        }
        return JsonResponse.success(0);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public JsonResponse cancel(@RequestParam() int userId, @RequestParam() int articleId) throws BusinessException
    {
        Collection collection = collectionService.findByUidAndArticleId(userId, articleId);
        if (null == collection) {
            throw new BusinessException(EmBusinessError.COLLECTION_NOT_EXISTED_ERROR);
        }
        collection.setStatus(2);
        if (!collectionService.update(collection)) {
            throw new BusinessException(EmBusinessError.ADD_COLLECTION_ERROR);
        }
        return JsonResponse.success(0);
    }

    /**
     * 是否收藏文章
     * @param userId
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/isCollected", method = RequestMethod.GET)
    public JsonResponse isCollected(@RequestParam() int userId, @RequestParam() int articleId)
    {
        boolean isCollected = collectionService.isCollected(userId, articleId);
        Map<String, Object> data = new HashMap<>();
        data.put("isCollected", isCollected);
        return JsonResponse.success(data);
    }
}
