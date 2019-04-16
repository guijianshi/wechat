package cn.qiuzhizhushou.wechat.util;

import cn.qiuzhizhushou.wechat.enums.CollectionTypeEnum;
import cn.qiuzhizhushou.wechat.model.Quote;
import cn.qiuzhizhushou.wechat.service.ArticleCollectionService;
import cn.qiuzhizhushou.wechat.vo.QuoteCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/4/16
 * Time: 下午6:18
 */
@Component
public class VOUtil
{
    @Autowired
    private ArticleCollectionService articleCollectionService;

    public QuoteCard trans(Quote quote)
    {
        QuoteCard quoteCard = new QuoteCard();
        quoteCard.setId(quote.getId());
        quoteCard.setContent(quote.getContent());
        quoteCard.setAuthorName(null == quote.getAuthor() ? "未知" : quote.getAuthor().getName());
        if (Token.isLogin()) {
            quoteCard.setIsCollection(
                    articleCollectionService.isCollected(
                            Token.getUserId(),
                            CollectionTypeEnum.QUOTE.getCode(), quote.getId())
            );
        }
        return quoteCard;
    }

    public List<QuoteCard> trans(List<Quote> quotes)
    {
        List<QuoteCard> quoteCards = new ArrayList<>();
        for (Quote quote : quotes) {
            quoteCards.add(trans(quote));
        }
        return quoteCards;
    }
}
