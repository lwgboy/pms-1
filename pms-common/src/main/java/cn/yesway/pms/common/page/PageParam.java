package cn.yesway.pms.common.page;

import java.io.Serializable;

/**
 * 分页参数的实体类。<br>
 * 设置对象的当前页数和每页记录数，进行数据库分页查询。
 * 
 * @author XuePeng
 */
public class PageParam implements Serializable {

    private static final long serialVersionUID = 6297178964005032338L;
    // 当前页数
    private int pageNum;
    // 每页记录数
    private int numPerPage;

    /**
     * 分页参数的构造函数。
     * 
     * @param 当前页数。
     * @param 每页记录数。
     */
    public PageParam(int pageNum, int numPerPage) {
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    /**
     * 获得当前页数。
     * 
     * @return 当前页数。
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置当前页数。
     * 
     * @param 当前页数。
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获得每页记录数。
     * 
     * @return 每页记录数。
     */
    public int getNumPerPage() {
        return numPerPage;
    }

    /**
     * 设置每页记录数。
     * 
     * @param 每页记录数。
     */
    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

}
