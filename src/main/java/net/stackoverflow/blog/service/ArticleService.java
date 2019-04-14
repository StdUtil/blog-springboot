package net.stackoverflow.blog.service;

import net.stackoverflow.blog.common.Page;
import net.stackoverflow.blog.pojo.po.ArticlePO;

import java.util.List;
import java.util.Map;

/**
 * 文章服务接口
 *
 * @author 凉衫薄
 */
public interface ArticleService {

    List<ArticlePO> selectByPage(Page page);

    List<ArticlePO> selectByCondition(Map<String, Object> searchMap);

    ArticlePO selectById(String id);

    ArticlePO selectByUrl(String url);

    ArticlePO insert(ArticlePO article);

    int batchInsert(List<ArticlePO> articles);

    ArticlePO deleteById(String id);

    int batchDeleteById(List<String> ids);

    ArticlePO update(ArticlePO article);

    int batchUpdate(List<ArticlePO> articles);

}
