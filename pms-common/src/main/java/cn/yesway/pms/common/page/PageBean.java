package cn.yesway.pms.common.page;

import java.io.Serializable;
import java.util.List;

import cn.yesway.pms.common.entity.BaseEntity;

/**
 * 分页组件。<br>
 * 用于前端DataGrid控件初始化。<br>
 * 组件内包含当前页、每页显示条数、总记录数、数据、总页数、页码索引等信息。
 * 
 * @author XuePeng
 */
public class PageBean<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = 8470697978259453214L;

    // 当前页
    private int currentPage;
    // 每页显示多少条
    private int numPerPage;

    // 总记录数
    private int totalCount;
    // 本页的数据列表
    private List<T> recordList;

    // 总页数
    private int pageCount;
    // 页码列表的开始索引（包含）
    private int beginPageIndex;
    // 页码列表的结束索引（包含）
    private int endPageIndex;

    /**
     * 分页组件的构造函数。 只接受前4个必要的属性，会自动的计算出其他3个属生的值。
     * 
     * @param 当前页。
     * @param 每页显示行数。
     * @param 总页数。
     * @param 本页的数据列表。
     */
    public PageBean(int currentPage, int numPerPage, int totalCount, List<T> recordList) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;

        // 计算总页码
        pageCount = (totalCount + numPerPage - 1) / numPerPage;

        // 计算 beginPageIndex 和 endPageIndex
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }
        // >> 总页数多于10页，则显示当前页附近的共10个页码
        else {
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }

    /**
     * @return 获得当前页。
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页。
     * 
     * @param 当前页。
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return 获得每页显示多少条。
     */
    public int getNumPerPage() {
        return numPerPage;
    }

    /**
     * 设置每页显示多少条。
     * 
     * @param 每页显示多少条。
     */
    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    /**
     * @return 获得总记录数。
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数。
     * 
     * @param 总记录数。
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return 获得本页的数据列表。
     */
    public List<T> getRecordList() {
        return recordList;
    }

    /**
     * 设置本页的数据列表。
     * 
     * @param 本页的数据列表。
     */
    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    /**
     * @return 获得总页数。
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 设置总页数。
     * 
     * @param 总页数。
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return 获得页码列表的开始索引（包含）。
     */
    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    /**
     * 设置页码列表的开始索引（包含）。
     * 
     * @param 页码列表的开始索引（包含）。
     */
    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    /**
     * @return 获得页码列表的结束索引（包含）。
     */
    public int getEndPageIndex() {
        return endPageIndex;
    }

    /**
     * 设置页码列表的结束索引（包含）。
     * 
     * @param 页码列表的结束索引（包含）。
     */
    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

}
