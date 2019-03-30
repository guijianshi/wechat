package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.Article;
import cn.qiuzhizhushou.wechat.model.Quote;
import cn.qiuzhizhushou.wechat.service.ArticleService;
import cn.qiuzhizhushou.wechat.service.AudioService;
import cn.qiuzhizhushou.wechat.service.QuoteService;
import cn.qiuzhizhushou.wechat.service.baidu.Api;
import cn.qiuzhizhushou.wechat.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

@RestController
@RequestMapping("wechat/audio")
public class AudioController extends BaseController
{
    @Autowired
    AudioService audioService;

    @Autowired
    QuoteService quoteService;

    @Autowired
    ArticleService articleService;

    @Autowired
    Api api;

    @GetMapping("/{type}/{id}")
    public Object get(@PathVariable String type, @PathVariable int id) throws BusinessException
    {
        if (!"quote".equals(type) && !"article".equals(type)) {
            id = 0;
        }
        String path = audioService.getPath(type, id);
        // 若资源不存在则根据内容查找资源包含内容,而后通过百度api生成
        if (!PathUtil.fileExist(path)) {
            try {
                String content;
                if ("quote".equals(type)) {
                    Quote quote = quoteService.findById(id);
                    if (null == quote) {
                        throw new BusinessException(EmBusinessError.QUOTE_NOT_EXIST_ERROR);
                    }
                    content = quote.getContent();
                } else {
                    Article article = articleService.findById(id);
                    if (null == article) {
                        throw new BusinessException(EmBusinessError.ARTICLE_NOT_EXIST_ERROR);
                    }
                    content = article.getContent();
                }
                if (content.length() == 0) {
                    throw new BusinessException(EmBusinessError.ARTICLE_NOT_EXIST_ERROR);
                }
                if (content.length() > 1024) {
                    content = content.substring(0, 1023);
                }
                api.tts(content, path);
            } catch (BusinessException e) {
                path = audioService.getPath("quote", 0);
            }
        }
        this.sayPlay(path);
        return null;
    }

    private void sayPlay(String filePath)
    {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == servletRequestAttributes) {
            return;
        }
        HttpServletResponse response = servletRequestAttributes.getResponse();
        if (null == response) {
            return;
        }
        try {
            File file = new File(filePath);

            response.setHeader("Content-Type", "audio/mp3");

            int len_l;

            byte[] buf = new byte[2048];

            FileInputStream fis = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            len_l = fis.read(buf);
            while (len_l != -1) {
                out.write(buf, 0, len_l);
                len_l = fis.read(buf);
            }
            out.flush();
            out.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
