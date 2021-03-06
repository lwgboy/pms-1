package cn.yesway.pms.core.cast.resource.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmount;
import cn.yesway.pms.core.cast.resource.entity.ResourceAmountDetail;

public interface ResourceAmountDao extends BaseDao<ResourceAmount> {

    int insertDetail(ResourceAmountDetail resourceAmountDetail);
    
    int insertProjectDetail(ResourceAmountDetail resourceAmountDetail);

    int deleteProjectDetail(Long id);
    
    int deleteDetail(Long id);
    
    int delete(Long id);

    int updateStatus(ResourceAmount resourceAmount);

    List<ResourceAmountDetail> listByResourceIdAndCreateDate(Map<String, Object> map);
    
    void createReport(Long id);
    
}
