package cn.yesway.pms.core.company.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.core.company.dao.CompanyDao;
import cn.yesway.pms.core.company.entity.Company;

/**
 * 公司(缴费主体)的业务处理类。
 * 
 * @author XuePeng
 */
@Component("companyBiz")
public class CompanyBiz {

	// 自动装配公司(缴费主体)的数据访问接口。
	@Autowired
	private CompanyDao companyDao;

	/**
	 * 根据主键查找公司(缴费主体)信息。
	 * 
	 * @param 公司(缴费主体)的主键。
	 * @return 公司(缴费主体)实体对象。
	 */
	public Company findById(Long id) {
		return companyDao.findById(id);
	}

	/**
	 * 查找全部公司(缴费主体)。
	 * 
	 * @return 公司(缴费主体)实体对象的集合。
	 */
	public List<Company> list() {
		return companyDao.listByParam(null);
	}

}
