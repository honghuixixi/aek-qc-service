package com.aek.ebey.qc.inter;

import com.baomidou.mybatisplus.plugins.Page;

public class LiuHuiFrontPage<T> {
	
	public static final int DEFAULT_PAGENO = 1;
	public static final int DEFAULT_PAGESIZE = 10;
	

	 //当前页数
    private Integer pageNo;
    
    //分页大小
    private Integer pageSize;

	

	 public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	//获取mybatisPlus封装的Page对象
    public Page<T> getPagePlus(){
    	int current = this.pageNo == null ? DEFAULT_PAGENO : this.pageNo;
		int size = this.pageSize == null ? DEFAULT_PAGESIZE : this.pageSize;
        Page<T> pagePlus = new Page<T>();
        pagePlus.setSize(size);
        pagePlus.setCurrent(current);
        return pagePlus;
    }

}
