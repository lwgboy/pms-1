package cn.yesway.pms.common.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yesway.pms.common.core.mybatis.interceptor.ExecutorInterceptor;
import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;

/**
 * 数据访问层基础支撑泛型类。
 * 
 * @author XuePeng
 *
 * @param <T>
 */
public abstract class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T> {

    /**
     * 新增。
     */
    private static final String SQL_INSERT = "insert";
    /**
     * 修改。
     */
    private static final String SQL_UPDATE = "update";
    /**
     * 根据主键删除。
     */
    private static final String SQL_DELETE_BY_ID = "deleteById";
    /**
     * 根据主键批量删除。
     */
    private static final String SQL_DELETE_BY_IDS = "deleteByIds";
    /**
     * 根据主键查找。
     */
    private static final String SQL_FIND_BY_ID = "findById";
    /**
     * 根据参数查找。
     */
    private static final String SQL_FIND_BY_PARAM = "findByParam";
    /**
     * 根据参数查找多个。
     */
    private static final String SQL_LIST_BY_PARAM = "listByParam";
    /**
     * 根据参数分页查找多个。
     */
    private static final String SQL_LIST_BY_PAGE = "listByPageAndParam";
    /**
     * 根据参数查找数量。
     */
    private static final String SQL_COUNT_BY_PARAM = "countByParam";

    // SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置)
    // 可以调用sessionTemplate完成数据库操作
    private SqlSessionTemplate sessionTemplate;

    // SqlSessionFactory实例(要求Spring中进行SqlSessionFactory的配置)
    // 可以调用sessionFactory打开一个SqlSession
    private SqlSessionFactory sessionFactory;

    /**
     * @return 获得一个SqlSessionTemplate对象。
     */
    public SqlSessionTemplate getSessionTemplate() {
        return this.sessionTemplate;
    }

    /**
     * 注入SqlSessionTemplate对象。
     */
    @Autowired
    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        super.setSqlSessionTemplate(sessionTemplate);
        this.sessionTemplate = sessionTemplate;
    }

    /**
     * @return 获得SqlSessionFactory对象。
     */
    public SqlSessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    /**
     * 注入SqlSessionFactory对象。
     */
    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
        super.setSqlSessionFactory(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * 新建实体对象。
     * 
     * @param 实体对象。
     * @return 新增成功后实体对象的主键。
     */
    @Override
    public long insert(T entity) {
        // 对数据库进行insert操作。
        // 执行Mapper配置文件中的insert方法。
        int result = sessionTemplate.insert(getStatement(SQL_INSERT), entity);
        // 判断如果要新建的实体对象不为null，并且成功保存到数据库了，则返回其主键。
        if (entity != null && entity.getId() != null && result > 0) {
            return entity.getId();
        }
        return result;
    }

    /**
     * 修改实体对象。
     * 
     * @param 实体对象。
     * @return 编辑成功的数量。
     */
    @Override
    public int update(T entity) {
        // 对数据库进行update操作，并返回影响行数。
        // 执行Mapper配置文件中的update方法。
        return sessionTemplate.update(getStatement(SQL_UPDATE), entity);
    }

    /**
     * 根据主键删除实体对象。
     * 
     * @param 实体对象的主键。
     * @return 删除成功的数量。
     */
    @Override
    public int deleteById(long id) {
        // 对数据库进行删除操作。
        // 执行Mapper配置文件中的deleteById方法。
        return sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
    }

    @Override
    public int deleteByIds(long... ids) {
        return sessionTemplate.delete(getStatement(SQL_DELETE_BY_IDS), ids);
    }

    /**
     * 根据主键查找实体对象。
     * 
     * @param 实体对象的主键。
     * @return 实体对象。
     */
    @Override
    public T findById(long id) {
        // 对数据库进行查询操作。
        // 执行Mapper配置文件中的findById方法。
        return sessionTemplate.selectOne(getStatement(SQL_FIND_BY_ID), id);
    }

    /**
     * 根据查询条件来查找实体对象。
     * 
     * @param 查询条件集合。
     * @return 实体对象。
     */
    @Override
    public T findByParam(Map<String, Object> paramMap) {
        // 对数据库进行查询操作。
        // 执行Mapper配置文件中的findByParam方法。
        if (paramMap == null || paramMap.isEmpty()) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_FIND_BY_PARAM), paramMap);
    }

    /**
     * 根据查询条件来查找实体对象集合。
     * 
     * @param 查询条件集合。
     * @return 实体对象的集合。
     */
    @Override
    public List<T> listByParam(Map<String, Object> paramMap) {
        // 对数据库进行查询操作。
        // 执行Mapper配置文件中的findListByParam方法。
        return sessionTemplate.selectList(getStatement(SQL_LIST_BY_PARAM), paramMap);
    }

    /**
     * 根据分页和条件查找实体对象集合。
     * 
     * @param 分页对象。
     * @param 查询条件集合。
     * @return 实体对象的集合。
     */
    @Override
    public PageBean<T> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap) {
        // 对数据库进行查询操作。
        // 执行Mapper配置文件中的findListByPageAndParam方法。
        HashMap<String, Object> params;
        if (paramMap == null) {
            params = new HashMap<>();
        } else {
            params = (HashMap<String, Object>) paramMap;
        }

        // 获取分页数据集 , 注切勿换成 sessionTemplate 对象。
        // 使用RowBounds进行分页。
        List<T> list = getSqlSession().selectList(getStatement(SQL_LIST_BY_PAGE), params,
                new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));

        // 统计总记录数
        Object countObject = (Object) getSqlSession().selectOne(getStatement(SQL_LIST_BY_PAGE),
                new ExecutorInterceptor.CountParameter(params));
        Long count = Long.valueOf(countObject.toString());

        return new PageBean<>(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
    }

    /**
     * 根据分页和条件查找实体对象集合。
     * 
     * @param 分页对象。
     * @param 查询条件集合。
     * @param 查询函数名称。
     * @return 实体对象的集合。
     */
    public PageBean<T> listByPageAndParam(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
        // 对数据库进行查询操作。
        // 执行Mapper配置文件中的自定义的方法。
        HashMap<String, Object> params;
        if (paramMap == null) {
            params = new HashMap<>();
        } else {
            params = (HashMap<String, Object>) paramMap;
        }

        // 获取分页数据集 , 注切勿换成 sessionTemplate 对象
        List<T> list = getSqlSession().selectList(getStatement(sqlId), params,
                new RowBounds((pageParam.getPageNum() - 1) * pageParam.getNumPerPage(), pageParam.getNumPerPage()));

        // 统计总记录数
        Object countObject = (Object) getSqlSession().selectOne(getStatement(sqlId),
                new ExecutorInterceptor.CountParameter(params));
        Long count = Long.valueOf(countObject.toString());

        return new PageBean<>(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
    }

    /**
     * 根据查询条件查询数据库中的记录数。
     * 
     * @param 查询条件集合。
     * @return 数据库中的记录数。
     */
    @Override
    public long countByParam(Map<String, Object> paramMap) {
        // 对数据库进行查询操作。
        // 执行Mapper配置文件中的findCountByParam方法。
        return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PARAM), paramMap);
    }

    /**
     * 通过sqlId创建全路径名的字符串。<br>
     * 通过当前类的getName()获得类的全名，拼接sqlId组成全路径方法名。
     * 
     * @param 要执行的方法名称。
     * @return 全路径的方法名称。
     */
    protected String getStatement(String sqlId) {
        String name = this.getClass().getName();
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
    }

}
