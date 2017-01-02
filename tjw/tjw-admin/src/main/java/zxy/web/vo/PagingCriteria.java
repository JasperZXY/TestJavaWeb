package zxy.web.vo;

import org.apache.ibatis.session.RowBounds;
import zxy.constants.JspConfig;

/**
 * 分页条件
 */
public class PagingCriteria {
    private Integer topage;
    private Integer pageSize;

    public RowBounds createRowBounds() {
        if (pageSize == null) {
            pageSize = JspConfig.PAGE_SIZE_DEFAULT;
        }
        if (pageSize > JspConfig.PAGE_SIZE_MAX) {
            pageSize = JspConfig.PAGE_SIZE_MAX;
        }

        if (topage == null) {
            topage = 1;
        }

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
