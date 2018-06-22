package com.aek.ebey.qc.inter;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

public class LiuHuiPage2<T> {
	

    //数据列表
    private List<T> records;

    //待巡检
    private int inspectingNum;
    
    //已巡检
    private int inspectedNum;
    
    /**
   	 * 下次巡检时间
   	 */
   	private Date nextDate;

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getInspectingNum() {
		return inspectingNum;
	}

	public void setInspectingNum(int inspectingNum) {
		this.inspectingNum = inspectingNum;
	}

	public int getInspectedNum() {
		return inspectedNum;
	}

	public void setInspectedNum(int inspectedNum) {
		this.inspectedNum = inspectedNum;
	}

	public LiuHuiPage2(Page<T> page){
        this.records = page.getRecords();
    }

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}


}
