package zxy.web.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询的数据
 * @author Jasper
 */
public class PagingResult<T> {
	/**
	 * 默认一页有多少条数据
	 */
	public final static int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 该页的第几条数据，从0开始
	 */
	private int start;
	/**
	 * 一页有多少条数据
	 */
	private int pageSize;
	/**
	 * 当前页存放的数据
	 */
	private List<T> result;
	
	/**
	 * 总记录条数
	 */
	private long count;
	
	public PagingResult(int start, long count, int pageSize, List<T> result) {
		this.start = start;
		this.count = count;
		this.pageSize = pageSize;
		this.result = result;
	}
	public PagingResult(int start, long count, int pageSize) {
		this(start, count, pageSize, new ArrayList<T>());
	}
	public PagingResult(int start, long count) {
		this(start, count, DEFAULT_PAGE_SIZE);
	}
	public PagingResult() {
		this(0, 0);
	}
	
	/**
	 * 根据要跳转的页数跟一页显示的条数，算出下标开始位置
	 * @param topage
	 * @param pageSize
	 * @return
	 */
	public static int getStart(int topage, int pageSize) {
		return (topage - 1) * pageSize;
	}
	
	/**
     * 取当前页码,页码默认1
     * @return 当前页码
     */
    public int getCurrentPageNo() {
        return start / pageSize + 1;
    }
    /**
     * 取总页数
     * @return 总页数
     */
    public long getTotalPageCount() {
        long pc = count / pageSize;
        return count % pageSize == 0 ? pc : pc + 1;
    }
	
	public int getStart() {
		return start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> list) {
		this.result = list;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
