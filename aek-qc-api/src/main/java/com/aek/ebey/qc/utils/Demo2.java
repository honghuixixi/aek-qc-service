package com.aek.ebey.qc.utils;

import java.util.ArrayList;
import java.util.List;

import com.aek.ebey.qc.request.Dept;

public class Demo2 {

	public static void main(String[] args) {
		List<Dept> list2=new ArrayList<>();
		Dept d=new Dept();
		d.setId(1L);
		d.setName("lisi");
		
		Dept d1=new Dept();
		d1.setId(2L);
		d1.setName("lisi2");
		list2.add(d);
		list2.add(d1);
		
		List<Dept> list=new ArrayList<>();
		Dept d3=new Dept();
		d3.setId(1L);
		d3.setName("lisi");
		list.add(d3);
		System.out.println(list2.removeAll(list));
		System.out.println(list2);
		
	
	}

}
