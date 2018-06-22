package com.aek.ebey.qc.request;

public class Dept {
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 科室名称
	 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Dept dept = (Dept) obj;
		if (id.longValue() == dept.getId().longValue()) {
			return true;
		} else {
			return false;
		}
	}
}
