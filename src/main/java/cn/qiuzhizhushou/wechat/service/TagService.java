package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.TagMapper;
import cn.qiuzhizhushou.wechat.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/25
 * Time: 上午11:48
 */
@Service
public class TagService
{
	@Autowired
	TagMapper tagMapper;


	public List<Tag> getHotTag()
	{
		return tagMapper.getHotTag();
	}
}
