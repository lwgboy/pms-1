package cn.yesway.pms.core.company.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.company.entity.Company;

/**
 * 公司(缴费主体)的数据访问类。
 * 
 * @author XuePeng
 */
@Component("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {

}
