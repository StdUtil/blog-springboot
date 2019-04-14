package net.stackoverflow.blog.service;

import net.stackoverflow.blog.common.Page;
import net.stackoverflow.blog.pojo.po.VisitPO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问量服务类接口
 *
 * @author 凉衫薄
 */
public interface VisitService {

    List<VisitPO> selectByPage(Page page);

    List<VisitPO> selectByCondition(Map<String, Object> searchMap);

    VisitPO selectById(String id);

    VisitPO insert(VisitPO visit);

    int batchInsert(List<VisitPO> visits);

    VisitPO deleteById(String id);

    int batchDeleteById(List<String> ids);

    VisitPO update(VisitPO visit);

    int batchUpdate(List<VisitPO> visits);

    List<VisitPO> selectByDate(Date startDate, Date endDate);

}
