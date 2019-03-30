package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class AudioService
{
    @Autowired
    QuoteService quoteService;

    private String quotePath = "/static/audio/quote/";

    private String articlePath = "/static/audio/article/";


    private String getQuotePath()
    {
        return PathUtil.pathRoot + this.quotePath;
    }

    // 根据资源类型获取资源应当存储的位置
    public String getPath(String type, int id)
    {
        String path;
        switch (type) {
            case "quote":
                path = getQuotePath();
                break;
            case "article":
                path = getArticlePath();
                break;
            default:
                path = getQuotePath();
                break;
        }

        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        return path + id + ".mp3";
    }

    private String getArticlePath()
    {
        return PathUtil.pathRoot + this.articlePath;
    }
}
