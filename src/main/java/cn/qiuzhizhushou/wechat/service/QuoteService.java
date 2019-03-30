package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.QuoteMapper;
import cn.qiuzhizhushou.wechat.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午4:32
 */
@Service
public class QuoteService
{
	@Autowired
	QuoteMapper quoteMapper;

	public Quote findById(int id)
	{
		return quoteMapper.findById(id);
	}

	public List<Quote> findByArticleId(int articleId)
	{
		return quoteMapper.findByArticleId(articleId);
	}


	/**
	 * 随机取一个名言
	 *
	 * @return 名言
	 */
	public Quote findOneByRand()
	{
		Random random = new Random();
		int id = random.nextInt(5000) + 1;
		return quoteMapper.findById(id);
	}

	public List<Quote> selectByIds(int[] ids)
	{
		return quoteMapper.selectByIds(ids);
	}
}
