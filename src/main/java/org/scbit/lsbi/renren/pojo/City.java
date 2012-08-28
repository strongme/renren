package org.scbit.lsbi.renren.pojo;

import java.util.List;

public class City {
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;
	
	private List<County> children;

	public List<County> getChildren() {
		return children;
	}

	public void setChildren(List<County> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", children=" + children + "]";
	}

	public City(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City() {
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
