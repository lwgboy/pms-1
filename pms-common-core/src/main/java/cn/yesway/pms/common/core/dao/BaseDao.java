package cn.yesway.pms.common.core.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;

/**
 * 数据访问类的基础支撑泛型接口。
 * 
 * @author XuePneg
 *
 * @param <T>
 */
public interface BaseDao<T extends BaseEntity> {

    /**
     * 新建实体对象。
     * 
     * @param 实体对象。
     * @return 新增成功后实体对象的主键。
     */
    long insert(T entity);

    /**
     * 修改实体对象。
     * 
     * @param 实体对象。
     * @return 编辑成功的数量。
     */
    int update(T entity);

    /**
     * 根据主键删除实体对象。
     * 
     * @param 实体对象的主键。
     * @return 删除成功的数量。
     */
    int deleteById(long id);

    /**
     * 根据主键删除实体对象。
     * 
     * @param 实体对象的主键。
     * @return 删除成功的数量。
     */
    int deleteByIds(long... id);

    /**
     * 根据主键查找实体对象。
     * 
     * @param 实体对象的主键。
     * @return 实体对象。
     */
    T findById(long id);

    /**
     * 根据查询条件来查找实体对象。
     * 
     * @param 查询条件集合。
     * @return 实体对象。
     */
    T findByParam(Map<String, Object> paramMap);

    /**
     * 根据查询条件来查找实体对象集合。
     * 
     * @param 查询条件集合。
     * @return 实体对象的集合。
     */
    List<T> listByParam(Map<String, Object> paramMap);

    /**
     * 根据分页和条件查找实体对象集合。
     * 
     * @param 分页对象。
     * @param 查询条件集合。
     * @return 实体对象的集合。
     */
    PageBean<T> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap);

    /**
     * 根据分页和条件查找实体对象集合。
     * 
     * @param 分页对象。
     * @param 查询条件集合。
     * @param 查询函数名称。
     * @return 实体对象的集合。
     */
    PageBean<T> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap, String sqlId);

    /**
     * 根据查询条件查询数据库中的记录数。
     * 
     * @param 查询条件集合。
     * @return 数据库中的记录数。
     */
    long countByParam(Map<String, Object> paramMap);
}
