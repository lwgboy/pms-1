package cn.yesway.pms.core.cast.resource.biz;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.resource.dao.ResourceDao;
import cn.yesway.pms.core.cast.resource.entity.Resource;

@Component("resourceBiz")
public class ResourceBiz {

    @Autowired
    private ResourceDao resourceDao;

    public Boolean create(Resource resource) {
        resource.setDeleted(Boolean.FALSE);
        LocalDateTime dt = LocalDateTime.now();
        resource.setGmtCreate(dt);
        resource.setGmtModified(dt);
        if (resourceDao.insert(resource) > 0) {
            return true;
        }
        return false;
    }

    public Boolean update(Resource resource) {
        resource.setGmtModified(LocalDateTime.now());
        if (resourceDao.update(resource) > 0) {
            return true;
        }
        return false;
    }

    public Boolean deleteById(Long... ids) {
        if (resourceDao.deleteById(ids) > 0) {
            return true;
        }
        return false;
    }

    public Resource findById(Long id) {
        return resourceDao.findById(id);
    }

    public PageBean<Resource> listByPageAndParam(PageParam page, Map<String, Object> map) {
        return resourceDao.listByPageAndParam(page, map);
    }
    
    public List<Resource> listByParam(Map<String, Object> map) {
        return resourceDao.listByParam(map);
    }

    public Boolean isExists(Map<String, Object> map) {
        if (resourceDao.countByParam(map) > 0) {
            return true;
        }
        return false;
    }
    
}
