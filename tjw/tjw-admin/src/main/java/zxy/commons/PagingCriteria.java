package zxy.commons;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页条件
 */
public class PagingCriteria {
    private Integer topage = 1;
    private Integer pageSize = JspConfig.PAGE_SIZE_DEFAULT;

    public RowBounds createRowBounds() {
//        if (pageSize == null) {
//            pageSize = JspConfig.PAGE_SIZE_DEFAULT;
//        }
        if (pageSize > JspConfig.PAGE_SIZE_MAX) {
            pageSize = JspConfig.PAGE_SIZE_MAX;
        }

//        if (topage == null) {
//            topage = 1;
//        }

        return new RowBounds(getStart(), pageSize);
    }

    public int getStart() {
        return PagingResult.getStart(topage, pageSize);
    }

    public Integer getTopage() {
        return topage;
    }

    public void setTopage(Integer topage) {
        this.topage = topage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
