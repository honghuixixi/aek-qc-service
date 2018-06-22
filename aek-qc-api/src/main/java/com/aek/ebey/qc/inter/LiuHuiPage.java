package com.aek.ebey.qc.inter;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

public class LiuHuiPage<T> {
	

    //数据列表
    private List<T> records;

    //总条数
    private int total;

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
    
	public LiuHuiPage(Page<T> page){
        this.total = page.getTotal();
        this.records = page.getRecords();
     
    }

}
