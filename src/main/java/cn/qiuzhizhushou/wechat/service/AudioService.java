package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.model.Quote;
import cn.qiuzhizhushou.wechat.service.baidu.Api;
import cn.qiuzhizhushou.wechat.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


@Service
public class AudioService
{
    @Autowired
    QuoteService quoteService;

    private String quotePath = "/static/audio/quote/";

    private String articlePath = "/static/audio/article/";


    public String getQuotePath(int id)
    {
        return PathUtil.pathRoot + this.quotePath + id + ".mp3";
    }

    // 根据资源类型获取资源应当存储的位置
    public String getPath(String type, int id)
    {
        String path;
        switch (type) {
            case "quote":
                path = getQuotePath(id);
                break;
            case "article":
                path = getArticlePath(id);
                break;
            default:
                path = getQuotePath(id);
                break;
        }
        return path;
    }

    private String getArticlePath(int id)
    {
        return PathUtil.pathRoot + this.articlePath + id + ".mp3";
    }
}
