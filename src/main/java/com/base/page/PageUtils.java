package cn.itcast.core.util;

/**
 * 分页工具类
 * @author:LiChong
 * @date:2018/11/18
 */
public class PageUtils {

	/**
	 * 当前页码
	 */
	private int pageNo = 1;

	/**
	 * 总记录数
	 */
	private int pageCount;

	/**
	 * 每页数量
	 */
	private int pageSize;

	/**
	 * 总记录数
	 */
	private int pageTotal;

	/**
	 * 开始行
	 */
	private int pageStartRow;



	public PageUtils(int pageNo, int pageCount , int pageSize) {
		if (pageNo > 0) {
			this.pageNo = pageNo;
		}
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		this.pageCount = pageCount;

		// 计算
		calculate();

	}

	/**
	 * 计算分页信息
	 */
	private void calculate() {

		pageStartRow = (pageNo - 1) * pageSize;
		if (pageSize > 0) {
			pageTotal = pageCount % pageSize == 0 ?  pageCount / pageSize : (pageCount / pageSize) + 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "PageUtils{" +
				"pageNo=" + pageNo +
				", pageCount=" + pageCount +
				", pageSize=" + pageSize +
				", pageTotal=" + pageTotal +
				", pageStartRow=" + pageStartRow +
				'}';
	}

	public static void main(String[] args) {

		PageUtils pageUtils = new PageUtils(2,22,5);
		System.out.println(pageUtils.toString());
	}
}
