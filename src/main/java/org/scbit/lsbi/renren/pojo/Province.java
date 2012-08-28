package org.scbit.lsbi.renren.pojo;

import java.util.List;

public class Province {
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Province(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private String name;
	
	private List<City> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getChildren() {
		return children;
	}

	public void setChildren(List<City> children) {
		this.children = children;
	}

/*	@Override
	public String toString() {
		return "Province [name=" + name + ", children=" + children + "]";
	}*/
	
	public Province() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean isParent;

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	
}
